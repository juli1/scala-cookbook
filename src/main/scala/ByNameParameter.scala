object ByNameParameter extends App{

  /*
   * In the following, timeit is a function and
   * the parameter myfunction is a by-name parameter
   * In the present case, we just know that
   * myfunction returns something that returns the type
   * A. Nothing is specified about the parameters themselves.
   */
  def timeit[A](myfunction: => A) = {
    val start = System.currentTimeMillis()
    val result = myfunction
    val end = System.currentTimeMillis()
    println(s"execution in ${end - start} ms to produce ${result}")
    result
  }

  override def main(args: Array[String]): Unit = {
    def bla(): Int = {
      return 3
    }

    def multiplyElementsBy2(l : List[Int]) = {
      l.map(_ * 2)
    }



    // We show there that we can pass function
    // without arguments or function with all the
    // argument there.
    // Note that the function is NOT executed before
    // with a result being put on the stack.
    timeit(bla())
    timeit(multiplyElementsBy2(List(2,3,4)))
    timeit({Thread.sleep(3000)})
  }
}