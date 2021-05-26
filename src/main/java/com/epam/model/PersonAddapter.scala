package com.epam.model



object PersonAddapter {

  implicit def convertToPerson(client:Client):Person = {
    new Person(name = client.name, age = client.age, email = client.email, phone = client.phone, address = null,company = null)
  }
}