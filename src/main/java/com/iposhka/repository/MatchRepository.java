package com.iposhka.repository;

import com.iposhka.exception.DatabaseException;
import com.iposhka.model.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends BaseRepository<Integer, Match>{
    private static final MatchRepository INSTANCE = new MatchRepository(Match.class);

    private MatchRepository(Class<Match> entityClass) {
        super(entityClass);
    }

    public static MatchRepository getInstance(){
        return INSTANCE;
    }

    public List<Match> findAllWithPagination(int pageSize, int page) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT m FROM Match as m", Match.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }catch (Exception e){
            throw new DatabaseException("Problems with database in find all with pagination");
        }
    }

    public List<Match> findAllWithFilterAndPagination(String filter, int pageSize, int page) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT m FROM Match as m WHERE m.player1.name ILIKE :filter OR m.player2.name ILIKE :filter", Match.class)
                    .setParameter("filter", "%" + filter + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }catch (Exception e){
            throw new DatabaseException("Problems with database in find all with pagination and filter");
        }
    }

    public long count(){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(m) FROM Match as m", Long.class)
                    .getSingleResult();
        }catch (Exception e){
            throw new DatabaseException("Problems with database with count matches");
        }
    }

    public long countByFilter(String filter){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(m) FROM Match as m WHERE m.player1.name ILIKE :filter OR m.player2.name ILIKE :filter", Long.class)
                    .setParameter("filter", "%" + filter + "%")
                    .getSingleResult();
        }catch (Exception e){
            throw new DatabaseException("Problems with database with count matches by filter");
        }
    }
}
