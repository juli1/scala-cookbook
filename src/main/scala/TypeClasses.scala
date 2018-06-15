/**
  * For more information about type classes, please see:
  *       https://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
  *
  * This is a very good resources to start with.
  */

/**
  * This is the type class! This is a type (trait) that take
  * a parameter (thing) fof type A.
  *
  * This is a contract that say we will support the operation
  * monthlyYield on the type A.
  * @tparam A
  */
trait YieldInterest[A]{
  def monthlyYield(thing: A): Double
}

// we define some basic classes that define a monthly rent and dividend.
case class House(rent: Int)
case class Stock(dividend: Int)

/**
  * Here, we define how the type class is implemented for our particular
  * classes. So here, we hare one class that is YieldHouse that will report
  * the monthly Yield for the house. Same thing for the stock.
  */
object YieldHouse extends YieldInterest[House] {
  def monthlyYield(thing: House): Double = thing.rent
}

object YieldStock extends YieldInterest[Stock] {
  def monthlyYield(thing: Stock): Double = thing.dividend
}



object TypeClasses extends App{

  /**
    * A function that use our type class. Here, the list of things must
    * be of the same type. So we can pass only a list of House or a list of
    * Stock. And the second parameter is the object that implements the type
    * class.
    */
  def computeDividends[A](things: List[A], method: YieldInterest[A]) = {
    things.map(thing => method.monthlyYield(thing)).sum
  }

  override def main(args: Array[String]): Unit = {
    val properties = List(House(1), House(10), House(42))
    val stocks = List(Stock(42), Stock(51), Stock(22))

    println(s"monthly yield for houses ${computeDividends(properties, YieldHouse)}")
    println(s"monthly yield for stocks ${computeDividends(stocks, YieldStock)}")
  }
}
