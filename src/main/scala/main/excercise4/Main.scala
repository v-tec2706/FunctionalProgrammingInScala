package main.excercise4

object Main {

  def main(args: Array[String]): Unit = {
    val options = List(Some(1), Some(2), Some(3))
    val optionsWithNone = options.::(None())
    println(Option.sequence(options))
    println(Option.sequence(optionsWithNone))

  }
}
