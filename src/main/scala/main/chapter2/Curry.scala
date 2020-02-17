package main.chapter2

object Curry {
  def main(args: Array[String]): Unit = {
    val b = curry((x: Int, y: Int) => x + y)
    println(s"Curried: b(1)(4):", b(1)(4))
    val c = uncurry(b)
    println(s"Uncurried c(1, 2): ", c(1, 2))

    val firstFunc = (x: Int) => x + 2
    val secondFunc = (x: Int) => x * 4
    val composed = compose(firstFunc, secondFunc)
    println(s"Composed (x+2)(x*2): ", composed(1))
  }

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    a: A => f(a, _)
  }

  def uncurry[A, B, C](f: A => (B => C)): (A, B) => C = {
    (a: A, b: B) => f(a)(b)

  }

  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    a: A => f(g(a))
  }
}
