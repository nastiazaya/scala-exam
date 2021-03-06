package com.epam.service


trait ValidLoaderDependency {

}

trait ValidDataLoaderDependency extends ValidLoaderDependency {
  val loader: FileLoader
}

trait ValidPersonJsonFileDataLoaderDependency extends ValidDataLoaderDependency {
  override val loader: FileLoader = JsonDataFileLoader
}

trait ValidClientExelFileDataLoaderDependency extends ValidDataLoaderDependency {
  override val loader: FileLoader = ExcelDataFileLoader
}

