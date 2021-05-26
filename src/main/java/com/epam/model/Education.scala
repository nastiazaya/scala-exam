package com.epam.model

object Education extends Enumeration {

  type Education = Value
  val LowerSecondary = Value("Lower secondary")
  val Doctoral = Value("Doctoral")
  val Bachelor = Value("Bachelor")
  val UpperSecondary = Value("Upper secondary")
  val Primary = Value("Primary")
  val Master = Value("Master")

}
