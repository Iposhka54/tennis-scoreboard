package com.iposhka.service;

import com.iposhka.dto.PlayerDto;
import com.iposhka.mapper.PlayerMapper;
import com.iposhka.model.Player;
import com.iposhka.repository.PlayerRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {
    private static final PlayerService INSTANCE = new PlayerService();
    private final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private final PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    public PlayerDto findOrCreateByName(String name){
        Player player = playerRepository.findByName(name)
                .orElseGet(() ->
                        playerRepository.save(playerMapper.toEntity(name)));
        return playerMapper.toDto(player);
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
