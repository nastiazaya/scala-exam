package com.epam.service

import com.epam.infra.ConfigLoader
import com.epam.model.UserRequest
import org.apache.spark.sql.SparkSession

import java.util.Properties
//Scala factory/Dependency Injection pattern implementation

trait UserDataLoadFlowDependency {

}

trait UserDataLoadFlowServiceDependency extends UserDataLoadFlowDependency {
  val config: Properties
  val validPersonDataLoader: ValidDataLoader
  val validClientDataLoader: ValidDataLoader
}



trait UserDataLoadFlowServiceImplDependency extends UserDataLoadFlowServiceDependency {
  val config: Properties = ConfigLoader.config
  val validPersonDataLoader: ValidDataLoader = new ValidPersonDataLoader(config.getProperty("data.path.jsonDataFile"))
  val validClientDataLoader: ValidDataLoader = new ValidClientsDataLoader(config.getProperty("data.path.excelDataFile"))
}

trait UserDataLoadFlowService {

  this: UserDataLoadFlowDependency =>

  def load(request: UserRequest)(implicit sparkSession: SparkSession): Unit

}



