package main.chapter2

import main.chapter2.Curry.{compose, curry, uncurry}
import main.chapter2.Fibonacci.takeNth

object Main {
  def main(args: Array[String]): Unit = {
    val b = curry((x: Int, y: Int) => x + y)
    println(s"Curried: b(1)(4):", b(1)(4))
    val c = uncurry(b)
    println(s"Uncurried c(1, 2): ", c(1, 2))

    val firstFunc = (x: Int) => x + 2
    val secondFunc = (x: Int) => x * 4
    val composed = compose(firstFunc, secondFunc)
    println(s"Composed (x+2)(x*2): ", composed(1))

    print(takeNth(358))

  }
}
