package cats.part4

/*
 * This covers the exercise of section 4.9 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.data.State
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}


object PostOrderCalculation extends App {

  type CalcState[A] = State[List[Int], A]

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  def operator(func: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case a :: b :: tail =>
        val ans = func(a, b)
        (ans :: tail, ans)

      case _ =>
        sys.error("Fail!")
    }

  def evalOne(sym: String): CalcState[Int] = {
    sym match {
      case "+" => operator(_ + _)
      case "*" => operator(_ * _)
      case "/" => operator(_ / _)
      case "-" => operator(_ - _)
      case num => operand(num.toInt)
    }
  }

  val i = evalOne("51").runA(Nil).value
  println(i)
  val program = for {
    _ <- evalOne("1")
    _ <- evalOne("2")
    ans <- evalOne("+")
  } yield ans

  val p = program.runA(Nil).value
  println(p)

  def evalAll(input: List[String]): CalcState[Int] = {
    input.foldLeft(0.pure[CalcState]) { (a, b) => a.flatMap(_ => evalOne(b))}
  }
  val program2 = evalAll(List("1", "2", "+", "3", "*"))
  // program: CalcState[Int] = cats.data.IndexedStateT@2e788ab0

  val r = program2.runA(Nil).value

  println(s"r=$r")
}
