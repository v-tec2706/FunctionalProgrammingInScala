package main.chapter7

import java.util.concurrent.ExecutorService

import scala.concurrent.Future

trait ParallelComputation {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A]

  def map2[A, B](par1: Par[A], par2: Par[A])(f: A => B): Par[B]

  def fork[A](a: => Par[A]): Par[A]

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def run[A](a: Par[A]): A
}