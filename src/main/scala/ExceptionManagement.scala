import java.net.InetAddress

import scala.util.{Success,Failure,Try}

object ExceptionManagement extends App{

  // Code to resolve domain to IP.
  def resolveName(domain: String): Try[String] = {
    // This will try to run the Java code and generate a Success(value)
    // if it runs correctly and otherwise, returns Failure(exception)
    Try(InetAddress.getByName(domain).getHostAddress)
  }

  override def main(args: Array[String]): Unit = {
    // This is the list of domains to resolve.
    val domains = List("twitter.com", "google.com", "foobar.gogol")

    // We map each IP into the domain name. If we have an exception, we return the Empty list.
    // We use flatMap because we flatten the list. If we have only map, we would have
    // List(List("A.B.C.D"), List("C.D.E.F"))
    // With flatMap, we have List("A.B.C.D", "C.D.E.F")
    val correspondingIp = domains.flatMap(domainName => resolveName(domainName) match {
      case Success(ip) => List(ip)
      case Failure(exception) => {
        println(s"Got exception while trying to resolve $domainName")
        List.empty
      }
    })
    println(s"List of IPs ${correspondingIp}")
  }

}
