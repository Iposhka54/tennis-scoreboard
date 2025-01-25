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

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
}
