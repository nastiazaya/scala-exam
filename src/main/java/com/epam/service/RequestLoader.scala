package com.epam.service

import com.epam.model.UserRequest

trait RequestLoader {

  def load(path:String): UserRequest

}
