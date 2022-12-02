package com.matyrobbrt.aoc2022

import scala.util.Using

object AOCUtils {
  def getDayAndParse[Z](day: Int, f: java.util.Iterator[String] => Z): Z = {
    Using(getClass.getResourceAsStream(f"/day$day.txt")) { inputStr =>
      val input = new String(inputStr.readAllBytes())
      val itr = input.lines().iterator()
      f(itr)
    }.get
  }
}