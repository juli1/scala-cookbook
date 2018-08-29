package cats.part4

/*
 * This covers the exercise of section 4.10 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Monad
import cats.syntax.functor._ // for map
import cats.syntax.flatMap._ // for flatMap


sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    Leaf(value)

  implicit val treeMonad = new Monad[Tree] {
    def pure[A](value: A): Tree[A] =
      Leaf(value)

    def flatMap[A, B](tree: Tree[A])
                     (func: A => Tree[B]): Tree[B] =
      tree match {
        case Branch(l, r) =>
          Branch(flatMap(l)(func), flatMap(r)(func))
        case Leaf(value) =>
          func(value)
      }

    def tailRecM[A, B](arg: A)
                      (func: A => Tree[Either[A, B]]): Tree[B] =
      func(arg) match {
        case Branch(l, r) =>
          Branch(
            flatMap(l) {
              case Left(l) => tailRecM(l)(func)
              case Right(l) => pure(l)
            },
            flatMap(r) {
              case Left(r) => tailRecM(r)(func)
              case Right(r) => pure(r)
            }
          )

        case Leaf(Left(value)) =>
          tailRecM(value)(func)

        case Leaf(Right(value)) =>
          Leaf(value)
      }
  }
}


object BranchingOut extends App {
  val branch: Tree[Int] = Tree.branch(Tree.leaf(100), Tree.leaf(200))
  val newBranch =  branch.flatMap(x => Tree.branch(Tree.leaf(x - 1), Tree.leaf(x + 1)))

  println(s"branch $branch")
  println(s"new branch $newBranch")

  val plop = for {
    a <- Tree.branch(Tree.leaf(100), Tree.leaf(200))
    b <- Tree.branch(Tree.leaf(a - 10), Tree.leaf(a + 10))
    c <- Tree.branch(Tree.leaf(b - 1), Tree.leaf(b + 1))
  } yield c
  println(s"plop $plop")
}
