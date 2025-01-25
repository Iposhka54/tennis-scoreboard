package com.iposhka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private int sets;
    private int games;
    private int points;

    public void wonPoint(){
        points++;
    }

    public void wonGame(){
        games++;
    }

    public void wonSet(){
        sets++;
    }

    public void clearPoints(){
        points = 0;
    }

    public void clearGames(){
        games = 0;
    }
}
