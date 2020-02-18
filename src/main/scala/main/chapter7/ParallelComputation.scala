package main.chapter7

import java.util.concurrent.ExecutorService

import scala.concurrent.duration.{Duration, TimeUnit}
import scala.concurrent.{CanAwait, ExecutionContext, Future}
import scala.util.Try

trait ParallelComputation {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A]

  def map2[A, B](par1: Par[A], par2: Par[A])(f: A => B): Par[B]

  def fork[A](a: => Par[A]): Par[A]

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def run[A](a: Par[A]): A
}

object Par {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A] = (es: ExecutorService) =>

  )

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] =
    (es: ExecutorService) => {
      val af = a(es)
      val bf = b(es)
      UnitFuture(f(af.value.get.get, bf.value.get.get))
    }

  def asyncF[A, B](f: A => B): A => Par[B]

  def parMap[A, B](ps: List[A])(f: A => B): Par[List[A]] = {
    val fbs: List[Par[B]] = ps.map(asyncF(f))
    null
  }

  private case class UnitFuture[A](get: A) extends Future[A] {
    def isDone = true

    def get(timeout: Long, units: TimeUnit) = get

    def isCancelled = false

    def cancel(evenIfRunning: Boolean): Boolean = false

    override def onComplete[U](f: Try[A] => U)(implicit executor: ExecutionContext): Unit = ???

    override def isCompleted: Boolean = true

    override def value: Option[Try[A]] = ???

    override def ready(atMost: Duration)(implicit permit: CanAwait): UnitFuture.this.type = ???

    override def result(atMost: Duration)(implicit permit: CanAwait): A = ???
  }

}
