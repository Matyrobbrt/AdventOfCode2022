package com.matyrobbrt.aoc2022

import java.nio.file.{Files, Path}
import scala.util.Using

object Day01 {
  def main(args: Array[String]): Unit = {
    part1()
    part2()
  }

  private def part1(): Unit = {
    AOCUtils.getDayAndParse(1, { itr =>
      var maxCalories: Int = 0
      var currentCalories: Int = 0
      while (itr.hasNext) {
        val line = itr.next()
        if (line.isEmpty) {
          maxCalories = if (currentCalories > maxCalories) currentCalories else maxCalories
          currentCalories = 0
        } else {
          currentCalories += Integer.valueOf(line)
        }
      }

      println(f"Part 1: $maxCalories")
    })
  }

  private def part2(): Unit = {
    AOCUtils.getDayAndParse(1, { itr =>
      val caloriesBuilder = List.newBuilder[Int]
      var currentCalories: Int = 0
      while (itr.hasNext) {
        val line = itr.next()
        if (line.isEmpty) {
          caloriesBuilder.addOne(currentCalories)
          currentCalories = 0
        } else {
          currentCalories += Integer.valueOf(line)
        }
      }

      val calories = caloriesBuilder.result().sorted
      println("Part 2: " + (calories.head + calories(1) + calories(2)))
    })
  }

}
