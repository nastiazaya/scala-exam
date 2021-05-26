package com.epam.infra

import org.apache.spark.sql.SparkSession

import java.util.Properties

trait ConfigurableSparkApp extends Application with SparkAppConfig {

  implicit val spark: SparkSession = SparkSession.builder()
    .master(config.getProperty("spark.master"))
    .appName(config.getProperty("spark.app.name"))
    .getOrCreate()


  override def main(args: Array[String]): Unit = run()

  def run()(implicit config: Properties, spark: SparkSession)
}
