package com.iposhka.service;

import com.iposhka.dto.MatchDto;
import com.iposhka.dto.OngoingMatch;
import com.iposhka.mapper.MatchMapper;
import com.iposhka.model.Match;
import com.iposhka.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService {
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    private final MatchRepository matchRepository = MatchRepository.getInstance();
    private final MatchMapper matchMapper = MatchMapper.INSTANCE;

    public MatchDto save(OngoingMatch match){
        Match entity = matchMapper.toEntity(match);
        entity.getPlayer1().setId(match.getPlayer1().getId());
        entity.getPlayer2().setId(match.getPlayer2().getId());
        return matchMapper.toDto(entity);
    }

    public static FinishedMatchesPersistenceService getInstance(){
        return INSTANCE;
    }
}
