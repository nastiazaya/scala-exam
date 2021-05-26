package com.epam.infra



import java.util.Properties
import scala.io.Source

trait SparkAppConfig extends AppConfig {
  implicit val config: Properties = ConfigLoader.config
}
