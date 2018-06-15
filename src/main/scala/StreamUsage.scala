import scala.util.Random._

// Stream API: https://www.scala-lang.org/api/current/scala/collection/immutable/Stream.html

object StreamUsage extends App{
  override def main(args: Array[String]): Unit = {

    // This single function defines a Stream[Int]. It continuously
    // returns Int types. The #:: combines the value and the stream.
    def randomNumbers: Stream[Int] = {nextInt(1000) #:: randomNumbers}

    // We are going to take 4 numbers and print them
    randomNumbers.take(4).foreach( randomNumber => println(randomNumber))

    // We are going to take numbers until we find 100
    randomNumbers.takeWhile(number => number != 100).foreach(println(_))

    println("with lazy val")

    // Here we need to put the value as lazy, otherwise, it would be computed/resolved
    // when we declare it and we want to resolve it when we use it.
    lazy val randomNumbersLazy: Stream[Int] = 1 #:: randomNumbersLazy.map(n => nextInt(1000))
    randomNumbersLazy.takeWhile(number => number != 100).foreach(println(_))

    println("done")
  }
}
