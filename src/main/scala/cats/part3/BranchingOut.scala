package cats.part3

/*
 * This covers the exercise of section 3.5 of the
 * Scala with cats book.
 * https://underscore.io/books/scala-with-cats/
 */

import cats.Functor
import cats.syntax.functor._

object BranchingOut extends App {
  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A])
    extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A,B](tree: Tree[A])(func: A => B): Tree[B] = {
      tree match {
        case Branch(left, right) =>
          Branch(map(left)(func), map(right)(func))
        case Leaf(value) =>
          Leaf(func(value))
      }
    }
  }

  object Tree {
    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
      Branch(left, right)

    def leaf[A](value: A): Tree[A] =
      Leaf(value)
  }

  val tree = Tree.branch(Tree.branch(Tree.leaf(2), Tree.leaf(3)), Tree.leaf(5))
  println(tree)
  val mappedTree = tree.map(v => v + 2)
  println(mappedTree)
  println(tree.map(_ * 2))
}