package cats

import cats.Monoid
import cats.kernel._
import cats.instances.int._
import cats.instances.string._
import cats.syntax.semigroup._

/*
 * This covers the exercise of section 2.5 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

object MonoidAddingAllTheThings extends App {
  case class Order(totalCost: Double, quantity: Double)

  implicit val monoidOrder: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = {
      Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    }
  }
  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    items.foldLeft(Monoid[A].empty)(_ |+| _)
  }
  val l = List(1,2,3,4)
  val s = add(l)
  println(s"sum $s")

  val order1 = Order(2, 3)
  val order2 = Order(3, 4)
  println(s"combine order = ${order1 |+| order2}")
}