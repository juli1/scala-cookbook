package cats

/*
 * This covers the exercise of section 1.5 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._

object CatEq extends App {

  final case class Cat(name: String, age: Int, color: String)

  var boubou1 = Cat("boubou", 10, "white")
  var boubou2 = Cat("boubou", 10, "white")
  var selene = Cat("selene", 4, "grey")

  implicit val catEqual = Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.age === cat2.age) && (cat1.name === cat2.name) && (cat1.color === cat2.color)
  }

  println(boubou1 === boubou2)
  println(boubou1 === selene)
  println(Option(boubou1) === None)
}