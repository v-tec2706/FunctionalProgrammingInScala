package main.chapter5

import main.chapter5.Stream._

object Main {
  def main(args: Array[String]): Unit = {
    val myStream = cons(1, cons(2, cons(3, empty)))
    val result = toList(take(2, myStream))
    println(result)
    println(toList(take(5, infiniteStream(1))))
  }
}
