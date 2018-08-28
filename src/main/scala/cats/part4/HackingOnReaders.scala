package cats.part4

/*
 * This covers the exercise of section 4.8 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.data.Reader
import cats.syntax.applicative._

object HackingOnReaders extends App {
  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).map(p => p == password).getOrElse(false))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      u <- findUsername(userId)
      check <- u.map(uname => checkPassword(uname, password)).getOrElse{false.pure[DbReader]}
    } yield check
}