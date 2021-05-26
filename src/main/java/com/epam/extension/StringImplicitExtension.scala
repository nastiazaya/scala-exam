package com.epam.extension

object StringImplicitExtension{
  private val regex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
  private val regex2 ="^\\d{3}[-]\\d{4}[-]\\d{2}$"

  implicit class EmailValidator(string: String){
    def isEmail: Boolean = string.contains("@") && string.endsWith(".com")
  }

  implicit class PhoneValidator(string: String){
    def isPhoneNumber: Boolean = string.matches(regex) || string.matches(regex2)
  }
}
