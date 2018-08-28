package cats.part4

/*
 * This covers the exercise of section 4.1 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import scala.language.higherKinds

object GettingFuncy extends App {
  trait Monad[F[_]] {
    def pure[A](a: A): F[A]

    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

    def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
  }

}