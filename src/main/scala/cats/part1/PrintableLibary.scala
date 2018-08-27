package cats.part1

/*
 * This covers the exercise of section 1.3 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val integerPrintable: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = value.toString
  }
}

object Printable {
  def format[A](value: A)(implicit w: Printable[A]): String = {
    w.format(value)
  }

  def print[A](value: A)(implicit w: Printable[A]): Unit = {
    println(w.format(value))
  }
}

/**
  * The goal of the PrintableSyntax obkect and the PrintableOps
  * class is to facilitate the use of Printable, We can just use
  * <obj>.print or <obj>.format instead of Printable.format(obj)
  */
object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def print(implicit p: Printable[A]): Unit = println(p.format(value))
  }
}