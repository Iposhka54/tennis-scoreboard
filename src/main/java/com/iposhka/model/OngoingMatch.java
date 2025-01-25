package com.iposhka.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OngoingMatch {
    private UUID id;
    private String player1;
    private String player2;

    @Builder.Default
    private Score player1Score = new Score();
    @Builder.Default
    private Score player2Score = new Score();
}
