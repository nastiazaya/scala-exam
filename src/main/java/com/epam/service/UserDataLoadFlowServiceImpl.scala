package com.epam.service
import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import com.epam.model.{Client, Contacts, Human, Person, UserRequest}
import com.epam.model.PersonAddapter._
import java.util.Properties
import scala.reflect.runtime.universe._

object UserDataLoadFlowServiceImpl extends UserDataLoadFlowService with UserDataLoadFlowServiceImplDependency {

  override def load(request: UserRequest)(implicit sparkSession: SparkSession): Unit = {
    import sparkSession.implicits._
    val personData = validPersonDataLoader.load[Person]

    val clientData =validClientDataLoader.load[Client]

    val filteredData = filterByRequestParams(request = request, dsClient = clientData, dsPerson = personData)


    val responseData = personData.union(clientData.map(p => convertToPerson(p)))
    responseData.show()

    //SaveJsonDataFile.save[Person](config.getProperty("data.path.responseJsonFile"),responseData)
  }

  private def filterByRequestParams(request: UserRequest, dsClient: Dataset[Client], dsPerson: Dataset[Person]): (Dataset[Client], Dataset[Person]) = {
    import dsPerson.sparkSession.implicits._
    request match {
      case UserRequest(Some(minAge), Some(maxAge), None, None, None, None) =>
        (
          dsClient.transform(FilterImpl.filterByAge[Client](_, min = minAge, max = maxAge)),
          dsPerson.transform(FilterImpl.filterByAge[Person](_, min = minAge, max = maxAge))
        )
      case UserRequest(None, None, Some(gender), None, Some(maritalStatus), Some(numberOfChildren)) => {
        (
          dsClient.transform(FilterImpl.filterByGenderMaritalStatusAndNumberOfChildren[Client](_, gender = gender,
            maritalStatus = maritalStatus, numOfChildren = numberOfChildren)),
          dsPerson
        )
      }
      case UserRequest(None, None, None, Some(prefixedName), None, None) => {
        (
          dsClient.transform(FilterImpl.filterNameByPrefix[Client](_, prefixedName)),
          dsPerson.transform(FilterImpl.filterNameByPrefix[Person](_, prefixedName))
        )
      }
      case UserRequest(Some(minAge), Some(maxAge), None, Some(prefixedName), None, None) => {
        val filteredDsClient = dsClient.transform(FilterImpl.filterByAge[Client](_, min = minAge, max = maxAge))
          .transform(FilterImpl.filterNameByPrefix[Client](_, prefixedName))
        val filteredDsPerson = dsPerson.transform(FilterImpl.filterByAge[Person](_, min = minAge, max = maxAge))
          .transform(FilterImpl.filterNameByPrefix[Person](_, prefixedName))
        (filteredDsClient, filteredDsPerson)
      }

      case UserRequest(None, None, Some(gender), Some(prefixedName), Some(maritalStatus), Some(numberOfChildren)) => {
        val filteredDsClient = dsClient.transform(FilterImpl.filterNameByPrefix[Client](_, prefixedName)
          .transform(FilterImpl.filterByGenderMaritalStatusAndNumberOfChildren[Client](_, gender = gender,
            maritalStatus = maritalStatus, numOfChildren = numberOfChildren)))
        (filteredDsClient, dsPerson)
      }
      case UserRequest(Some(minAge), Some(maxAge), Some(gender), None, Some(maritalStatus), Some(numberOfChildren)) => {
        val filteredDsClient = dsClient.transform(FilterImpl.filterByAge[Client](_, min = minAge, max = maxAge))
          .transform(FilterImpl.filterByGenderMaritalStatusAndNumberOfChildren[Client](_, gender = gender,
            maritalStatus = maritalStatus, numOfChildren = numberOfChildren))
        val filteredDsPerson = dsPerson.transform(FilterImpl.filterByAge[Person](_, min = minAge, max = maxAge))
        (filteredDsClient, filteredDsPerson)
      }
      case UserRequest(Some(minAge), Some(maxAge), Some(gender), Some(prefixedName), Some(maritalStatus), Some(numberOfChildren)) => {
        val filteredDsClient = dsClient.transform(FilterImpl.filterByAge[Client](_, min = minAge, max = maxAge))
          .transform(FilterImpl.filterNameByPrefix[Client](_, prefixedName))
          .transform(FilterImpl.filterByGenderMaritalStatusAndNumberOfChildren[Client](_, gender = gender,
            maritalStatus = maritalStatus, numOfChildren = numberOfChildren))

        val filteredDsPerson = dsPerson.transform(FilterImpl.filterByAge[Person](_, min = minAge, max = maxAge))
          .transform(FilterImpl.filterNameByPrefix[Person](_, prefixedName))

        (filteredDsClient, filteredDsPerson)
      }
      case _ => {
        println("error")
        (dsClient, dsPerson)
      }
    }
  }


}