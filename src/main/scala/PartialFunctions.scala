
object PartialFunctions extends App{

  /*
   * First, we define a function with two argument
   * list.
   */
  def multiply(a: Int)(b: Int): Int = a * b

  /*
   * Then, we define a partially applied function
   * that will use multiply and still requires one
   * argument.
   * Thus, multiplyBy2 is a function itself.
   */
  def multiplyBy2 = multiply(2)(_)

  override def main(args: Array[String]): Unit = {
    println(s"2 * 2 = ${multiplyBy2(3)}")
  }
}
