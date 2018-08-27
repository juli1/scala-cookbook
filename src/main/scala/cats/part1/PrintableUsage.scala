package cats.part1

/*
 * This covers the exercise of section 1.3 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.part1.PrintableInstances._
import cats.part1.PrintableSyntax.PrintableOps


object PrintableUsage extends App {

  final case class Cat(name: String, age: Int, color: String)

  val i = 3
  val s = "hello world"
  println("format="+Printable.format(i))
  Printable.print(s)
  var cat = Cat("boubou", 10, "white")

  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    def format(value: Cat): String = {
      s"I am a ${value.color} cat named ${value.name} and I'm ${value.age} old"
    }
  }
  Printable.print(cat)

  cat.print // using the PrintableOps
  println(cat.format) // using PrintableOps too (for .format)
}