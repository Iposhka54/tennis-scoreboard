package com.iposhka.service;

import com.iposhka.model.OngoingMatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private final Map<UUID, OngoingMatch> matches = new ConcurrentHashMap<>();

    public UUID createMatch(String name1, String name2){
        OngoingMatch match = OngoingMatch.builder()
                .id(UUID.randomUUID())
                .player1(name1)
                .player2(name2)
                .build();
        matches.put(match.getId(), match);
        return match.getId();
    }

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
}
