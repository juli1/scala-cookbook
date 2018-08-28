package cats.part4

/*
 * This covers the exercise of section 4.7 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Eval
import cats.data.Writer

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.syntax.writer._
import cats.syntax.applicative._
import cats.instances.vector._


object ShowYourWorking extends App {
  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

  def factorial(n: Int): Int = {
    val ans = slowly(if(n == 0) 1 else n * factorial(n - 1))
    println(s"fact $n $ans")
    ans
  }

  Await.result(Future.sequence(Vector(
    Future(factorial(3)),
    Future(factorial(3))
  )), 5.seconds)

  println(s"factorial 5 = ${factorial(5)}")

  type Logged[A] = Writer[Vector[String], A]

  def factorialWriter(n: Int): Logged[Int] =
    for {
      ans <- if(n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorialWriter(n - 1).map(_ * n))
      }
      _   <- Vector(s"fact $n $ans").tell
    } yield ans
  val (log, res) = factorialWriter(5).run
  println(s"result $log -> $res")

}