package cats

/*
 * This covers the exercise of section 1.4 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

final case class Cat(name: String, age: Int, color: String)

object CatShow extends App {
  val i = 3
  val s = "hello world"
  println("format="+Printable.format(i))
  Printable.print(s)
  var cat = Cat("boubou", 10, "white")

  implicit val catShowable = Show.show[Cat] { cat =>
    s"I'm a ${cat.color} cat called ${cat.name} and I am ${cat.age} years old"
  }

  println(cat.show)
}