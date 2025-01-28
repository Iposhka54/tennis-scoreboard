package com.iposhka.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchDto {
    PlayerDto player1;
    PlayerDto player2;
    PlayerDto winner;
}
