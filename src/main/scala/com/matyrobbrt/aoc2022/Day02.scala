package com.matyrobbrt.aoc2022

import RockPaperScissorsGame.Result
import RockPaperScissorsGame.Move

object Day02 {
  def main(args: Array[String]): Unit = {
    part1()
    part2()
  }

  private def part1(): Unit = {
    AOCUtils.getDayAndParse(2, { itr =>
      val game = createGame()
      while (itr.hasNext) {
        val moves = itr.next().split(" ")
        game.play(moves(0), moves(1))
      }

      println(f"Part 1: ${game.getYourScore}")
    })
  }

  private def part2(): Unit = {
    AOCUtils.getDayAndParse(2, { itr =>
      val game = createGame()
      while (itr.hasNext) {
        val moves = itr.next().split(" ")
        game.playAndExpectResult(moves(0), moves(1))
      }

      println(f"Part 2: ${game.getYourScore}")
    })
  }

  private def createGame(): RockPaperScissorsGame = new RockPaperScissorsGame()
    .defineMove("a", Move.ROCK)
    .defineMove("b", Move.PAPER)
    .defineMove("c", Move.SCISSORS)

    .defineMove("x", Move.ROCK)
    .defineMove("y", Move.PAPER)
    .defineMove("z", Move.SCISSORS)

    .defineResultOutcome(Result.LOSE, 0)
    .defineResultOutcome(Result.DRAW, 3)
    .defineResultOutcome(Result.WIN, 6)

    .defineExpectedResult("x", Result.LOSE)
    .defineExpectedResult("y", Result.DRAW)
    .defineExpectedResult("z", Result.WIN)
}
