package cats.part3

/*
 * This covers the exercise of section 3.6 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */


object InvariantFunctors extends App {
  trait Codec[A] {
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = {
      val self = this

      new Codec[B] {
        override def encode(value: B): String = self.encode(enc(value))

        override def decode(value: String): B = dec(self.decode(value))
      }
    }
  }

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }
  implicit val intCodec: Codec[Int] =
    stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)

  /* let's define a codec for double */
  implicit val doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)

  case class Box[A](value: A)

  /* let's define a codec for the box */
  implicit def boxCodec[A] (implicit c: Codec[A]): Codec[Box[A]] =
    c.imap[Box[A]](Box(_), _.value)

  println(encode(123.4))
  println(encode(Box("blabla")))

}