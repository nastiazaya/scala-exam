package com.epam.model

case class Person(age:Int, name: String, company: Option[String], email: String, phone: String, address: Option[String])
  extends Human with Contacts
