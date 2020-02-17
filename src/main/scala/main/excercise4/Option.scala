package main.excercise4

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B]

  def flatMap[B](f: A => Option[B]): Option[B]

  def getOrElse[B >: A](default: => B): B

  def orElse[B >: A](ob: => Option[B]): Option[B]

  def filer(f: A => Boolean): Option[A]
}

case class Some[+A](get: A) extends Option[A] {
  override def map[B](f: A => B): Option[B] = Some(f(get))

  override def flatMap[B](f: A => Option[B]): Option[B] = f(get)

  override def getOrElse[B >: A](default: => B): B = get

  override def filer(f: A => Boolean): Option[A] = if (f(get)) Some(get) else None()

  override def orElse[B >: A](ob: => Option[B]): Option[B] = {
    get match {
      case Some(get: B) => Some(get)
      case _ => ob
    }
  }
}

case class None() extends Option[Nothing] {
  override def map[B](f: Nothing => B): Option[B] = None()

  override def flatMap[B](f: Nothing => Option[B]): Option[B] = None()

  override def getOrElse[B >: Nothing](default: => B): B = default

  override def filer(f: Nothing => Boolean): Option[Nothing] = None()

  override def orElse[B >: Nothing](ob: => Option[B]): Option[B] = ???
}

object Option {
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    (a, b) match {
      case (Some(val1), Some(val2)) => Some(f(val1, val2))
      case _ => None()
    }
  }

  def sequence[A](a: List[Option[A]]): Option[List[Option[A]]] = {
    @scala.annotation.tailrec
    def sequenceRec[B](a: List[Option[B]], res: List[Some[B]]): Option[List[Option[B]]] = {
      a match {
        case Some(x) :: Nil => Some(res.::(Some(x)))
        case Some(x) :: tail => sequenceRec(tail, res.::(Some(x)))
        case None() :: _ => None()
      }
    }

    sequenceRec(a, List[Some[A]]())
  }
}
