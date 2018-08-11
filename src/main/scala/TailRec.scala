/**
  * Basic example of the @tailrec annotation. This guarantee
  * that the call is tail-recursive and we will not
  * hit a stack overflow exception.
  */

import scala.annotation.tailrec

object TailRec extends App{


  def sumList(list: List[Int]): Int = {
    @tailrec
    def sumListRec(l : List[Int], accumulator: Int): Int = {
      l match {
        case Nil => accumulator
        case h :: t => sumListRec(t, accumulator + h)
      }
    }
    sumListRec(list, 0)
  }

  override def main(args: Array[String]): Unit = {
    val l = List(3,4,5)
    println(s"list sum = ${sumList(l)}")
  }
}
