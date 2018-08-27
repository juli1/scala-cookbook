package cats.part1

/*
 * This covers the exercise of section 1.4 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Show
import cats.syntax.show._


object CatShow extends App {

  final case class Cat(name: String, age: Int, color: String)

  var cat = Cat("boubou", 10, "white")

  implicit val catShowable = Show.show[Cat] { cat =>
    s"I'm a ${cat.color} cat called ${cat.name} and I am ${cat.age} years old"
  }

  println(cat.show)
}