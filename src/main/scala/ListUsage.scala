import model._

object ListUsage extends App {
  val data: List[Animal] = List(
    Dog("chewie"),
    Cat("boubou"),
    Cat("selene"),
    Cat("fido"),
    Dog("bombon"),
    new Animal("cow")
  )

  // 1. Print all the animals
  for (a <- data) {
    println(s"Animal ${a}")
  }

  // 2. checking with a for loop all animals that are a dog
  for (animal <- data ;
      if animal.isInstanceOf[Dog])
  {
    println(s"This is a dog named ${animal.name}")
  }

  // 3. Looking at the for loop below, this will eventually
  //    get translated as the following piece of code.
  data.filter( a => a.isInstanceOf[Dog]).foreach{ animal => println(s"This is a dog named ${animal.name}")}


  // 4. We filter the animals with "bo" in their name and filter and print.
  val animalsWithBoInName = for (animal <- data ;
       if animal.name.contains("bo"))
    yield {animal.name}


  for (animal <- animalsWithBoInName) {
    println(s"v1: ${animal}")
  }

  // 5. The previous operation with the for can be written like this with map/filter
  data.filter(a => a.name.contains("bo")).map(a => a.name).foreach(n => println(s"v2: ${n}"))

}
