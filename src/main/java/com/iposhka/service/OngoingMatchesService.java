package com.iposhka.service;

import com.iposhka.dto.PlayerDto;
import com.iposhka.dto.OngoingMatch;
import com.iposhka.exception.MatchNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private final Map<UUID, OngoingMatch> matches = new ConcurrentHashMap<>();

    public UUID createMatch(PlayerDto player1, PlayerDto player2){
        OngoingMatch match = OngoingMatch.builder()
                .id(UUID.randomUUID())
                .player1(player1)
                .player2(player2)
                .build();
        matches.put(match.getId(), match);
        return match.getId();
    }

    public OngoingMatch findMatchByUUID(UUID uuid){
        OngoingMatch match = matches.get(uuid);
        if(match == null){
            throw new MatchNotFoundException("Match with " + uuid.toString() + " not found");
        }
        return match;
    }

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
}
