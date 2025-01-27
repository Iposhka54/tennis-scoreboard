package com.iposhka.service;

import com.iposhka.dto.MatchDto;
import com.iposhka.dto.OngoingMatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService {
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    public MatchDto save(OngoingMatch match){
        return MatchDto.builder().build();
    }

    public static FinishedMatchesPersistenceService getInstance(){
        return INSTANCE;
    }
}
