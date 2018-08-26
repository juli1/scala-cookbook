package cats

/*
 * This covers the exercise of section 2.3 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.instances.int._
import cats.instances.option._
import cats.instances.string._
import cats.syntax.eq._

object TruthMonoid extends App {
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }

  val booleanOrMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  val booleanAndMonoid = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  println(Monoid(booleanAndMonoid).combine(true, false))
  println(Monoid(booleanAndMonoid).combine(true, true))
}