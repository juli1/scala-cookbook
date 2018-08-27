package cats.part3

/*
 * This covers the exercise of section 3.6 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */



object Contramap extends App {
  trait Printable[A] {
    self =>
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] =
      new Printable[B] {
        def format(value: B): String =
          self.format(func(value))
      }
  }

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  println(format("hello"))
  println(format(true))
  println(format(false))

  final case class Box[A](value: A)

  // how to make a box printable using old way
//  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
//    new Printable[Box[A]] {
//      def format(box: Box[A]): String = {
//        s"box containing ${p.format(box.value)}"
//      }
//    }

  // use contramap to combine calls
  implicit def boxPrintableCM[A](implicit p: Printable[A]) = p.contramap[Box[A]](_.value)

  println(format(Box("hello world")))
  println(format(Box(true)))
}