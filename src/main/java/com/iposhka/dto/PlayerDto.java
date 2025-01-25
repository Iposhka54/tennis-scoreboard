package com.iposhka.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class PlayerDto {
    private Integer id;
    private String name;
}
