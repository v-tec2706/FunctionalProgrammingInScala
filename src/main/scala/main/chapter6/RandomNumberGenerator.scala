package main.chapter6

trait RandomNumberGenerator {
  def nextInt: (Int, RandomNumberGenerator)
}

case class SimpleRNG(seed: Long) extends RandomNumberGenerator {
  override def nextInt: (Int, RandomNumberGenerator) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }


  def nonNegativeInt(rng: RandomNumberGenerator): (Int, RandomNumberGenerator) = {
    (Math.abs(rng.nextInt._1), rng)
  }

  def genDouble(): Double = {
    Int.MaxValue.toDouble
  }
}
