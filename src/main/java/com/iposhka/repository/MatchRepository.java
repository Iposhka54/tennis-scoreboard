package com.iposhka.repository;

import com.iposhka.model.Match;

public class MatchRepository extends BaseRepository<Integer, Match>{
    private static final MatchRepository INSTANCE = new MatchRepository(Match.class);

    private MatchRepository(Class<Match> entityClass) {
        super(entityClass);
    }

    public static MatchRepository getInstance(){
        return INSTANCE;
    }
}
