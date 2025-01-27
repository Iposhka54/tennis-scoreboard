package com.iposhka.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ScoreFormatter {
    private static final int POINTS_MIN = 4;

    public static String getFormattedPoints(int playerPoints, int opponentPoints, boolean isTieBreak){
        if(isTieBreak){
            return String.valueOf(playerPoints);
        }
        if(playerPoints == opponentPoints && playerPoints >= POINTS_MIN){
            return "Same";
        }

        if (playerPoints >= POINTS_MIN || opponentPoints >= POINTS_MIN) {
            if (playerPoints > opponentPoints) {
                return "More";
            }else{
                return "Less";
            }
        }

        String[] scoreLabels = {"0", "15", "30", "40"};
        return scoreLabels[Math.min(playerPoints, POINTS_MIN - 1)];
    }
}
