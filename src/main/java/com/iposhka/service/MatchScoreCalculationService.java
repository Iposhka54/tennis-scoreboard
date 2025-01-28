package com.iposhka.service;

import com.iposhka.dto.OngoingMatch;
import com.iposhka.dto.Score;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();
    private static final int POINTS_TO_WIN_GAME = 1;
    private static final int GAMES_TO_WIN_SET = 1;
    private static final int TIEBREAK_POINTS_TO_WIN = 7;
    private static final int MIN_ADVANTAGE = 2;
    private static final int SETS_TO_WIN = 1;

    public void calculateScore(OngoingMatch match, Integer winnerId){
        Score winnerScore =  match.getPlayer1().getId().equals(winnerId)
                ? match.getPlayer1Score()
                : match.getPlayer2Score();

        Score opponentScore = match.getPlayer1().getId().equals(winnerId)
                ? match.getPlayer2Score()
                : match.getPlayer1Score();

        winnerScore.wonPoint();

        if(isTieBreak(winnerScore, opponentScore)){
            checkTieBreak(winnerScore, opponentScore);
        }else{
            checkGameResult(winnerScore, opponentScore);
            checkSetResult(winnerScore, opponentScore);
        }
    }


    public boolean isMatchOver(OngoingMatch match){
        return match.getPlayer1Score().getSets() == SETS_TO_WIN
                || match.getPlayer2Score().getSets() == SETS_TO_WIN;
    }

    public boolean isTieBreak(Score player1, Score player2) {
        return player1.getGames() == GAMES_TO_WIN_SET && player2.getGames() == GAMES_TO_WIN_SET;
    }

    private void checkGameResult(Score winnerScore, Score opponentScore){
        if (winnerScore.getPoints() >= POINTS_TO_WIN_GAME
                && winnerScore.getPoints() - opponentScore.getPoints() >= MIN_ADVANTAGE) {
            winnerScore.wonGame();
            clearPoints(winnerScore, opponentScore);
        }
    }

    private void checkSetResult(Score winnerScore, Score opponentScore){
        if (winnerScore.getGames() >= GAMES_TO_WIN_SET
                && winnerScore.getGames() - opponentScore.getGames() >= MIN_ADVANTAGE) {
            winnerScore.wonSet();
            clearGames(winnerScore, opponentScore);
        }
    }

    private void checkTieBreak(Score winnerScore, Score opponentScore) {
        if (winnerScore.getPoints() >= TIEBREAK_POINTS_TO_WIN) {
            winnerScore.wonSet();
            clearGames(winnerScore, opponentScore);
            clearPoints(winnerScore, opponentScore);
        }
    }

    private void clearGames(Score... scores){
        for (Score score : scores) {
            score.clearGames();
        }
    }

    private void clearPoints(Score... scores){
        for (Score score : scores) {
            score.clearPoints();
        }
    }

    public static MatchScoreCalculationService getInstance(){
        return INSTANCE;
    }
}
