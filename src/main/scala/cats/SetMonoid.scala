package cats

/*
 * This covers the exercise of section 2.4 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

object SetMonoid extends App {
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }

  implicit def setMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(s1: Set[A], s2: Set[A]): Set[A] = s1 ++ s2
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def combine(a: Int, b: Int) = a + b
    def empty = 0
  }
  val intSetMonoid = Monoid[Set[Int]]
  println(intSetMonoid.combine(Set(1, 2), Set(2, 3)))
}