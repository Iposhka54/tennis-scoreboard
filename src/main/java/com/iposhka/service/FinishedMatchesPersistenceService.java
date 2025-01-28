package com.iposhka.service;

import com.iposhka.dto.MatchDto;
import com.iposhka.dto.OngoingMatch;
import com.iposhka.mapper.MatchMapper;
import com.iposhka.mapper.PlayerMapper;
import com.iposhka.model.Match;
import com.iposhka.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService {
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    private final MatchRepository matchRepository = MatchRepository.getInstance();
    private final MatchMapper matchMapper = MatchMapper.INSTANCE;

    public MatchDto save(OngoingMatch match){
        Match entity = matchMapper.toEntity(match);

        Integer playerId1 = match.getPlayer1().getId();
        Integer playerId2 = match.getPlayer2().getId();

        entity.getPlayer1().setId(playerId1);
        entity.getPlayer2().setId(playerId2);

        int player1Sets = match.getPlayer1Score().getSets();
        int player2Sets = match.getPlayer2Score().getSets();

        if (player1Sets > player2Sets) {
            entity.getWinner().setId(playerId1);
            entity.getWinner().setName(match.getPlayer1().getName());
        } else{
            entity.getWinner().setId(playerId2);
            entity.getWinner().setName(match.getPlayer2().getName());

        }
        matchRepository.save(entity);
        return matchMapper.toDto(entity);
    }

    public List<MatchDto> findAll(String filter, int pageSize, int page){
        List<Match> matches;
        if(filter == null){
            matches = matchRepository.findAllWithPagination(pageSize, page);
            return matchMapper.toDto(matches);
        }else{
            filter = filter.toLowerCase();
            matches = matchRepository.findAllWithFilterAndPagination(filter, pageSize, page);
        }
        return matchMapper.toDto(matches);
    }

    public long count(String filter){
        long count;
        if(filter == null){
            count = matchRepository.count();
        }else{
            count = matchRepository.countByFilter(filter);
        }
        return count;
    }


    public static FinishedMatchesPersistenceService getInstance(){
        return INSTANCE;
    }
}
