package model

import scala.util.{Try, Success, Failure}

class UnimplementedException (message: String = null, cause: Throwable = null) extends Exception(message, cause)

class Animal(n: String) {
  val name = n
  def noise(): Try[String] = {
    throw new UnimplementedException()
  }

  override def toString: String = {
    s"Animal ${name}"
  }
}

class Dog(name: String) extends Animal(name) {
  override def noise(): Try[String] = {
    Success(s"$name barks ouah ouah ouah")
  }
}

object Dog {
  def apply(name: String): Dog = new Dog(name)
}


class Cat(name: String) extends Animal(name) {
  override def noise(): Try[String] = {
    Success(s"$name miaow ouah ouah ouah")
  }
}

object Cat {
  def apply(name: String): Cat = new Cat(name)
}