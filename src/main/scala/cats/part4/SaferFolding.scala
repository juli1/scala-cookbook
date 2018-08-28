package cats.part4

/*
 * This covers the exercise of section 4.6 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Id
import cats.Eval

object SaferFolding extends App {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    as match {
      case head :: tail =>
        fn(head, foldRight(tail, acc)(fn))
      case Nil =>
        acc
    }

  // let's re-implement foldRight with Eval and without
  // having recursion issues.
  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }


  val l = List(2,3,4)
  val f = foldRight(l, 0)(_ + _)
  println(f)
  val fe = foldRightEval(l, Eval.now(0)){(a: Int, b: Eval[Int]) => Eval.now(a + b.value)}
  println(fe.value)
}