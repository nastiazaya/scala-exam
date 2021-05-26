import com.epam.infra.ConfigurableSparkApp
import com.epam.service.{JsonRequestLoader, UserDataLoadFlowServiceImpl}
import org.apache.spark.sql.SparkSession

import java.util.Properties

object SparkFilterApp extends ConfigurableSparkApp {
  override def run()(implicit config: Properties, spark: SparkSession): Unit = {

    val request = JsonRequestLoader.load(config.getProperty("data.path.requestJsonFile"))
    UserDataLoadFlowServiceImpl.load(request)


  }
}
