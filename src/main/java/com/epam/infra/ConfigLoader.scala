package com.epam.infra

import java.util.Properties
import scala.io.Source

object ConfigLoader {
  val config: Properties = {
    println("singletone initialized")
    val url = getClass.getResource("/application.properties")
    val properties: Properties = new Properties()
    val source = Source.fromURL(url)
    properties.load(source.bufferedReader())
    source.close()
    properties
  }
}
