object OptionUsage extends App {

  /**
    * Try to find the method for a particular URL
    * @param url The URL to find the method for
    * @return the method if found, None otherwise
    */
  def getMethod(url: String): Option[String] = {

    // url.split returns an array
    val s = url.split(":")

    // so here, we splitted http://blabla into two strings: http and ://blabla
    // therefore, for any valid URL with a method, the size of the array will be 2. If the size is more than
    // 2 we return the first element of the array (the head)
    // if the size of the array is 1, it means there is no match, we just return None
    s.length match {
      case 0 | 1 => None
      case _ => Some(s.head)
    }
  }

  override def main(args: Array[String]): Unit = {
    // Some URLs to use to find the method
    val urls = List("http://www.yahoo.com", "ftp://ftp.idsoftware.com", "foobar")

    // For each URL, we try to find the corresponding method and then, print it.
    // If there is no method, then, we print that we do not find any method
    urls.map(url => getMethod(url)).foreach( _ match {
      case Some(v) => println(s"method $v")
      case None => println(s"Impossible to find a method for url")
    })
  }

}
