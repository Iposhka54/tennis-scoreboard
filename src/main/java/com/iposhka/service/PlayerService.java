package com.iposhka.service;

import com.iposhka.dto.PlayerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {
    private static final PlayerService INSTANCE = new PlayerService();

    public static PlayerDto findOrCreate(String name){
        return null;
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
