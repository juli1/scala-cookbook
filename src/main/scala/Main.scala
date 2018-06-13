import model.House

object Main extends App {
  val c = House(1, "7111 Church St", 15218, "Pittsburgh", "PA")


  println("Hello, World!" + s"${c.toString}")
}