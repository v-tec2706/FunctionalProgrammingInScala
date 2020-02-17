package main.chapter2

import scala.annotation.tailrec

object Fibonacci {

  @tailrec
  def fib(prev: Int, cur: Int, i: Int, n: Int): Int = {
    if (i == n) cur else fib(cur, prev + cur, i + 1, n)
  }

  def takeNth(n: Int): Int = {
    n match {
      case 1 => 0
      case 2 => 1
      case _ => fib(0, 1, 2, n)
    }
  }

  def main(args: Array[String]): Unit = {
    print(takeNth(358))
  }
}
