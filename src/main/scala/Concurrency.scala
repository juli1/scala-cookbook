import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object Concurrency extends App{
  override def main(args: Array[String]): Unit = {
    val l = List(1,2,3,4)

    // map List[Integer] into List[Future[Int]] and the produces
    // result is a multiplication by two of the argument.
    val futs = l.map(value => Future{
      println(s"Future $value, going to sleep");
      Thread.sleep(value * 1000);
      println(s"Future $value coming back from sleeping")
      value * 2
    })

    // Convert List[Future] into Future[List]
    val fut = Future.sequence(futs)

    // we register a callback when the future is really done.
    fut.onComplete(l => l match {
      case Success(li) => println(li)
      case Failure(ex) => println("exception")
    })

    // This will be shown before all the futures completes
    println("Program done?")

    // In fact, we need to wait for the future to be completed.
    Await.result(fut, Duration.Inf)

    // The program is really done here.
    println("Program really done")
  }

}
