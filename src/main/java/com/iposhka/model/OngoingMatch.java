package com.iposhka.model;

import com.iposhka.dto.PlayerDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OngoingMatch {
    private UUID id;
    private PlayerDto player1;
    private PlayerDto player2;

    @Builder.Default
    private Score player1Score = new Score();
    @Builder.Default
    private Score player2Score = new Score();
}
