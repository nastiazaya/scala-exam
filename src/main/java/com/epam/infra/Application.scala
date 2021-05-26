package com.epam.infra



trait Application {

  // self reference
  this: AppConfig =>
  def main(args: Array[String]): Unit

}
