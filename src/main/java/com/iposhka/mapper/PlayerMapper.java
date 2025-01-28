package com.iposhka.mapper;

import com.iposhka.dto.PlayerDto;
import com.iposhka.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDto toDto(Player player);

    Player toEntity(PlayerDto playerDto);

    Player toEntity(String name);
}
