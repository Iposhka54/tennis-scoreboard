package com.iposhka.mapper;

import com.iposhka.dto.MatchDto;
import com.iposhka.dto.OngoingMatch;
import com.iposhka.model.Match;
import com.iposhka.model.Player;
import com.iposhka.service.PlayerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = PlayerMapper.class)
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);
    PlayerService playerService = PlayerService.getInstance();

    MatchDto toDto(Match match);

    List<MatchDto> toDto(List<Match> matches);

    @Mapping(target = "player1", source = "player1")
    @Mapping(target = "player2", source = "player2")
    @Mapping(target = "winner",source = ".", qualifiedByName = "determineWinner")
    Match toEntity(OngoingMatch match);

    @Named("determineWinner")
    default Player determineWinner(OngoingMatch match){
        int player1Sets = match.getPlayer1Score().getSets();
        int player2Sets = match.getPlayer2Score().getSets();

        if (player1Sets >= 2 && player1Sets > player2Sets) {
            return PlayerMapper.INSTANCE.toEntity(match.getPlayer1());
        } else{
            return PlayerMapper.INSTANCE.toEntity(match.getPlayer2());
        }
    }
}
