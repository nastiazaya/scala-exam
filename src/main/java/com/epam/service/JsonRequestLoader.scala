package com.epam.service
import com.epam.model.{Gender, MaritalStatus, UserRequest}
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.ext._

import scala.io.Source


object JsonRequestLoader extends RequestLoader {
  implicit val formats: Formats = DefaultFormats + new EnumNameSerializer(Gender) + new EnumNameSerializer(MaritalStatus)

  override def load(path: String): UserRequest = {
    val jsonFileSource = Source.fromFile(path)
    val jsonString = jsonFileSource.mkString
    jsonFileSource.close()
    val jsValue = parse(jsonString)

    jsValue.extract[UserRequest]

  }
}
