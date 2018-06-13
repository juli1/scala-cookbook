import scala.util.{Failure, Random, Success}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

object Concurrency extends App{
  class IntForPrime(val number: Int) {
    def isPrime: Boolean = {
      number match {
        case 1 => true
        case x => Nil == {
          2 to math.sqrt(number).toInt filter (y => x % y == 0)
        }
      }
    }
  }

  implicit def IntToIntForPrime(i: Int) = new IntForPrime(i)

  def findRandomPrimeNumber: Future[Int] = {
    // we promise we will find this value!
    val primeFound = Promise[Int]
    val rand = new Random()

    // We wrap the work executing our promise inside a future.
    Future {
      // We loop until we found a prime number
      while (true){
        val n = rand.nextInt(1000)
        if (n.isPrime) {
          // we return from the promise
          primeFound.success(n)
        }
      }
    }

    // We return the future corresponding to our promise
    primeFound.future
  }

  override def main(args: Array[String]): Unit = {
    val futurePrime = findRandomPrimeNumber
    futurePrime.onComplete( fut => fut match {
      case Success(v) => println(s"Prime number found: $v")
      case Failure(e) => println(s"Exception found while trying to find the prime number")
    })


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
      case Success(prime) => println(s"Prime number found $prime")
      case Failure(ex) => println("exception")
    })

    // This will be shown before all the futures completes
    println("Program done?")

    // In fact, we need to wait for the future to be completed.
    Await.result(fut, Duration.Inf)

    // The program is really done here.
    println("Program really done")

    // The prime future was not finished!
    Await.result(futurePrime, Duration.Inf)

    // The program is **really** done here.
    println("Program REALLY done")
  }

}
