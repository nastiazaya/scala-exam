package com.epam.service


import com.epam.model.Client
import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}
import scala.reflect.runtime.universe._

object ExcelDataFileLoader extends FileLoader {



  override def load[T <: Product  : Encoder :  TypeTag](path: String)(implicit sparkSession: SparkSession): Dataset[T] = {
    val schema = Encoders.product[T].schema
    sparkSession.read
      .format("com.crealytics.spark.excel")
      .option("sheetName", "Random generator") // Required
      .option("header", "true") // Required
      .option("treatEmptyValuesAsNulls", "true") // Optional, default: true
      //.option("inferSchema", "false") // Optional, default: false
      .option("inferSchema", "false") // Optional, default: false
      .option("addColorColumns", "true") // Optional, default: false
      .option("startColumn", 0) // Optional, default: 0
      .option("endColumn", 99) // Optional, default: Int.MaxValue
      .option("timestampFormat", "MM-dd-yyyy HH:mm:ss") // Optional, default: yyyy-mm-dd hh:mm:ss[.fffffffff]
      .option("maxRowsInMemory", 20) // Optional, default None. If set, uses a streaming reader which can help with big files
      .option("excerptSize", 10) // Optional, default: 10. If set and if schema inferred, number of rows to infer schema from
      .schema(schema)  // Optional, default: Either inferred schema, or all columns are Strings
      .load(path).as[T]
  }
}
