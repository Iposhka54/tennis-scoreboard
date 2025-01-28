package com.iposhka.service;

import com.iposhka.dto.OngoingMatch;
import com.iposhka.dto.PlayerDto;
import com.iposhka.dto.Score;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MatchScoreCalculationServiceTest {
    private static MatchScoreCalculationService service;
    private static OngoingMatch match;
    private static PlayerDto ivan;
    private static PlayerDto petya;

    @BeforeAll
    static void init(){
        service = MatchScoreCalculationService.getInstance();
    }

    @BeforeEach
    void initMatch(){
        ivan = PlayerDto.builder()
                .id(1)
                .name("Ivan Pupkin")
                .build();
        petya = PlayerDto.builder()
                .id(2)
                .name("Petya Pupkin")
                .build();
        Score firstScore = new Score();
        Score secondScore = new Score();

        match = OngoingMatch.builder()
                .player1(ivan)
                .player2(petya)
                .player1Score(firstScore)
                .player2Score(secondScore)
                .build();
    }

    @Test
    void shouldNotEndGameOnAdvantageAfterDeuce(){
        Integer player1Id = match.getPlayer1().getId();
        Integer player2Id = match.getPlayer2().getId();
        for(int i = 0; i < 3; i++){
            service.calculateScore(match, player1Id);
        }
        for(int i = 0; i < 3; i++){
            service.calculateScore(match, player2Id);
        }

        service.calculateScore(match, player1Id);
        int games1Player = match.getPlayer1Score().getGames();
        int games2Player = match.getPlayer2Score().getGames();
        int points1Player = match.getPlayer1Score().getPoints();
        int points2Player = match.getPlayer2Score().getPoints();

        assertThat(games1Player).isEqualTo(0);
        assertThat(games2Player).isEqualTo(0);
        assertThat(points1Player).isEqualTo(4);
        assertThat(points2Player).isEqualTo(3);
    }

    @Test
    void shouldWinGameWhenPlayer1WinsPointAt40To0(){
        Integer player1Id = match.getPlayer1().getId();
        for(int i = 0; i < 4; i++){
            service.calculateScore(match, player1Id);
        }
        int games1Player = match.getPlayer1Score().getGames();
        int games2Player = match.getPlayer2Score().getGames();
        int points1Player = match.getPlayer1Score().getPoints();
        int points2Player = match.getPlayer2Score().getPoints();

        assertThat(games1Player).isEqualTo(1);
        assertThat(games2Player).isEqualTo(0);
        assertThat(points1Player).isEqualTo(0);
        assertThat(points2Player).isEqualTo(0);
    }

    @Test
    void shouldStartTiebreakWhenScoreIs6To6(){
        Score player1Score = match.getPlayer1Score();
        Score player2Score = match.getPlayer2Score();
        player1Score.setGames(6);
        player2Score.setGames(6);

        assertThat(service.isTieBreak(player1Score, player2Score)).isEqualTo(true);
    }

    @Test
    void shouldWinSetWhenWinningGameAt6To4(){
        Score player1Score = match.getPlayer1Score();
        Score player2Score = match.getPlayer2Score();
        player1Score.setGames(5);
        player2Score.setGames(4);
        player1Score.setPoints(3);

        service.calculateScore(match, match.getPlayer1().getId());

        assertThat(player1Score.getSets()).isEqualTo(1);
        assertThat(player2Score.getSets()).isEqualTo(0);
        assertThat(player1Score.getGames()).isEqualTo(0);
        assertThat(player2Score.getGames()).isEqualTo(0);
    }

    @Test
    void shouldEndMatchAfterWinningTwoSets(){
        match.getPlayer1Score().setSets(2);
        match.getPlayer2Score().setSets(1);

        assertThat(service.isMatchOver(match)).isEqualTo(true);
    }

    @Test
    void shouldWinSetWhenGameAt6to6AndPoints7At0(){
        Score player1Score = match.getPlayer1Score();
        Score player2Score = match.getPlayer2Score();
        player1Score.setGames(6);
        player2Score.setGames(6);
        player1Score.setPoints(6);

        service.calculateScore(match, match.getPlayer1().getId());

        assertThat(player1Score.getSets()).isEqualTo(1);
        assertThat(player2Score.getSets()).isEqualTo(0);
        assertThat(player1Score.getPoints()).isEqualTo(0);
        assertThat(player2Score.getPoints()).isEqualTo(0);
    }
}