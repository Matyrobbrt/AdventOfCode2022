package com.matyrobbrt.aoc2022;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class RockPaperScissorsGame {
    private final Map<String, Move> moves = new HashMap<>();
    private final Map<String, Result> expectedResults = new HashMap<>();
    private final Map<Result, Integer> results = new HashMap<>();

    private int opponentScore, yourScore;

    public RockPaperScissorsGame defineMove(String id, Move move) {
        this.moves.put(id.toLowerCase(Locale.ROOT), move);
        return this;
    }

    public RockPaperScissorsGame defineResultOutcome(Result result, int outcome) {
        this.results.put(result, outcome);
        return this;
    }
    
    public RockPaperScissorsGame defineExpectedResult(String id, Result result) {
        this.expectedResults.put(id.toLowerCase(Locale.ROOT), result);
        return this;
    }

    public int getOpponentScore() { return this.opponentScore; }
    public int getYourScore() { return this.yourScore; }

    public void play(Move opponentMove, Move yourMove) {
        if (opponentMove == yourMove) {
            opponentScore += results.get(Result.DRAW);
            yourScore += results.get(Result.DRAW);
        } else if (opponentMove.defeatedMy(yourMove)) {
            yourScore += results.get(Result.WIN);
            opponentScore += results.get(Result.LOSE);
        } else {
            opponentScore += results.get(Result.WIN);
            yourScore += results.get(Result.LOSE);
        }

        opponentScore += opponentMove.score;
        yourScore += yourMove.score;
    }
    
    public void play(String opponent, String you) {
        final Move opponentMove = moves.get(opponent.toLowerCase(Locale.ROOT));
        final Move yourMove = moves.get(you.toLowerCase(Locale.ROOT));
        play(opponentMove, yourMove);
    }

    public void playAndExpectResult(String opponent, String expected) {
        final Move opponentMove = moves.get(opponent.toLowerCase(Locale.ROOT));
        final Result expectedResult = expectedResults.get(expected.toLowerCase(Locale.ROOT));
        play(opponentMove, switch (expectedResult) {
            case WIN -> opponentMove.getDefeatingOpponent();
            case DRAW -> opponentMove;
            case LOSE -> opponentMove.getOpponentToDefeat();
        });
    }

    public enum Move {
        ROCK("paper", 1),
        PAPER("scissors", 2),
        SCISSORS("rock", 3);

        private Move isDefeatedBy;
        private final String isDefeatedByStr;
        
        private Move defeats;
        
        public final int score;

        Move(String isDefeatedByStr, int score) {
            this.isDefeatedByStr = isDefeatedByStr.toUpperCase(Locale.ROOT);
            this.score = score;
        }

        public Move getDefeatingOpponent() {
            if (isDefeatedBy != null) return isDefeatedBy;
            return isDefeatedBy = valueOf(isDefeatedByStr);
        }
        
        public Move getOpponentToDefeat() {
            if (defeats != null) return defeats;
            return defeats = Stream.of(values())
                    .filter(it -> it.defeatedMy(this))
                    .findFirst().orElseThrow();
        }

        public boolean defeatedMy(Move other) {
            return getDefeatingOpponent() == other;
        }
    }

    public enum Result {
        LOSE,
        DRAW,
        WIN
    }
}
