object ImplicitUsage extends App{

  case class Prefix(s: String)

  /**
    * The addPrefix add the prefix to the string passed as
    * parameter. The prefix is another parameter passed
    * in the second parameter list passed as implicit.
    * @param s
    * @param p
    * @return the string with the added prefix
    */
  def addPrefix(s: String)(implicit p: Prefix): String = {
    p.s + s
  }


  override def main(args: Array[String]): Unit = {
    /**
      * Here, we define an implicit foo with the type Prefix.
      * The keyword implicit means that this value of this particular
      * type can be used by an implicit parameter for a function.
      *
      * In the present case, this foo implicit will be used when
      * we call addPrefix.
      */
    implicit val foo = Prefix("foo")
    val bar = "bar"

    // in the following call, the second parameter (p) does not need
    // to be passed because it is implicit. We use the implicit val foo
    // instead.
    val foobarImplicit = addPrefix(bar)

    // show "foobar"
    println(s"Implicit $foobarImplicit")

    // Here, we pass explicitly another prefix to the function. We do not
    // use the implicit val.
    val foobarExplicit = addPrefix("baz")(Prefix(foobarImplicit))

    println(s"Explicit $foobarExplicit")

  }

}
