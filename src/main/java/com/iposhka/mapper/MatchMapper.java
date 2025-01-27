package com.iposhka.mapper;

import com.iposhka.dto.MatchDto;
import com.iposhka.dto.OngoingMatch;
import com.iposhka.model.Match;
import com.iposhka.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PlayerMapper.class)
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(target = "id", ignore = true)
    MatchDto toDto(Match match);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player1", source = "player1")
    @Mapping(target = "player2", source = "player2")
    @Mapping(target = "winner",source = ".", qualifiedByName = "determineWinner")
    MatchDto toDto(OngoingMatch match);

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
