package main.chapter5

sealed trait Stream[+A]

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def apply[A](as: A*): Stream[A] = {
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
  }

  def take[A](n: Int, stream: Stream[A]): Stream[A] = {
    def takeN(stream: Stream[A], res: Stream[A], n: Int): Stream[A] = {
      (stream, n) match {
        case (_, 0) => res
        case (Cons(head, tail), n) => takeN(tail.apply(), cons(head.apply(), res), n - 1)
        case _ => empty
      }
    }

    takeN(stream, empty, n)
  }

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def infiniteStream[A](a: A): Stream[A] = {
    def elements: Stream[A] = cons(a, elements)

    elements
  }

  def toList[A](stream: Stream[A]): List[A] = {
    stream match {
      case Empty => List[A]()
      case Cons(head, tail) => toList(tail.apply()) ::: List[A](head.apply())
    }
  }
}

