package com.iposhka.service;

import com.iposhka.dto.OngoingMatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();

    public void calculateScore(OngoingMatch match, Integer winnerId){
        if(match.getPlayer1().getId().equals(winnerId)){
            match.getPlayer1Score().wonPoint();
        }else{
            match.getPlayer2Score().wonPoint();
        }
    }

    public boolean isMatchOver(OngoingMatch match){
        return false;
    }

    public static MatchScoreCalculationService getInstance(){
        return INSTANCE;
    }
}
