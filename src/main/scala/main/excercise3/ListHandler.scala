package main.excercise3

import scala.annotation.tailrec

object ListHandler {
  def tail[A](list: List[A]): List[A] = {
    list match {
      case Nil => Nil
      case (_: A) :: (rest: List[A]) => rest
    }
  }

  def setHead[A](list: List[A], element: A): List[A] = {
    list match {
      case Nil => Nil
      case _ :: tail => element :: tail
    }
  }

  @tailrec
  def dropWhile[A](list: List[A])(predicate: A => Boolean): List[A] = {
    list match {
      case Nil => Nil
      case x :: tail => if (predicate(x)) dropWhile(tail)(predicate) else list
    }
  }

  def init[A](list: List[A]): List[A] = {
    list match {
      case Nil => Nil
      case x => tail(x.reverse).reverse
    }
  }

  def lengthFR[A](list: List[A]): Int = {
    list.foldRight(0)((a, b) => {
      println(s"foldRight($a, $b)") // this print helps to visualise evaluation of execution
      b + 1
    })
  }

  def lengthFL[A](list: List[A]): Int = {
    list.foldLeft(0)((a, b) => {
      println(s"foldLeft($a, $b)") // this print helps to visualise evaluation of execution
      a + 1
    })
  }

  def reverseFL[A](list: List[A]): List[A] = {
    list.foldLeft(List[A]())((x, y) => {
      println(s"foldLeft($x, $y)") // this print helps to visualise evaluation of execution
      x.::(y)
    })
  }

  def append[A](list: List[A], elem: A): List[A] = {
    list.foldLeft(List[A](elem))((x, y) => x.::(y)).reverse
  }

  def concatenate[A](list1: List[A], list2: List[A]): List[A] = {
    list2.foldRight(list1)((x, y) => y.::(x))
  }

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] = {
    list.foldRight(List[B]())((x, y) => y.:::(f(x)))
  }

  @tailrec
  def zipWithBase[A](acc: List[A], list1: List[A], list2: List[A])(function: (A, A) => A): List[A] = {
    (list1, list2) match {
      case (Nil, Nil) => acc
      case (x :: tail1, y :: tail2) => zipWithBase(acc.::(function(x, y)), tail1, tail2)(function)
    }
  }

  def zipWith[A](list1: List[A], list2: List[A])(function: (A, A) => A): List[A] = {
    zipWithBase(List[A](), list1, list2)(function).reverse
  }

  def main(args: Array[String]): Unit = {
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
  }
}
