package main.chapter3

import main.chapter3.ListHandler._
import main.chapter3.Tree.{map, maximum, size}

object Main {
  def main(args: Array[String]): Unit = {

    // lists
    val someList = List(11, 22, 33, 44, 55)

    println(tail(tail(someList)))
    println(setHead(someList, 6))
    println(dropWhile(someList)(_ < 3))
    println(init(someList))
    println(lengthFR(someList))
    println(lengthFL(someList))
    println(reverseFL(someList))
    println(append(someList, 66))
    println(concatenate(someList, someList))
    println(flatMap(someList)(i => List(i, i, i)))
    println(zipWith(List(1, 2, 3), List(4, 5, 6))((x, y) => x + y))

    // tree
    val tree: Tree[Int] = Branch(Leaf(1), Branch(Leaf(2), Leaf(8)))

    println(size(tree))
    println(maximum(tree))
    println(map(tree)(x => x + 3))
  }
}