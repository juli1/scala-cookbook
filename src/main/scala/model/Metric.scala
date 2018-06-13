package model

object MetricKind extends Enumeration {
  val MIN, MAX, COUNT = Value
}

case class Metric(value: Int, kind: MetricKind.Value)
