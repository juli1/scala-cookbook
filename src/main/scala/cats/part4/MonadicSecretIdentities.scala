package cats.part4

/*
 * This covers the exercise of section 4.3 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Id

object MonadicSecretIdentities extends App {
  def pure[A](value: A): Id[A] = value

  def map[A, B](initial: Id[A])(func: A => B): Id[B] =
    func(initial)

  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] =
    func(initial)

  println(pure(3))
  println(map(pure(3.toInt))(v => v + 2))
}