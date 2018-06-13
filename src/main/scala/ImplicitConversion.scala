import model.{Metric, MetricKind}

object ImplicitConversion extends App{

  // We define a function implicit that can convert an integer into a Metric
  implicit def intToMetric(i: Int): Metric = {
    Metric(i, MetricKind.COUNT)
  }

  override def main(args: Array[String]): Unit = {
    // The type we associate to the value is a Metric. We assign an integer.
    // Therefore, the compiler will try to convert the integer (42) to a Metric.
    // It will use the implicit function intToMetric to do this.
    val metric: Metric = 42

    println(s"Metric: $metric") // print Metric(42,COUNT)
  }
}
