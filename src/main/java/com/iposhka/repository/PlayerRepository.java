package com.iposhka.repository;

import com.iposhka.exception.DatabaseException;
import com.iposhka.model.Player;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Integer, Player>{
    private static final PlayerRepository INSTANCE = new PlayerRepository(Player.class);
    public PlayerRepository(Class<Player> entityClass) {
        super(entityClass);
    }

    public Optional<Player> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Player player = session.createQuery("select p from Player as p where p.name = :name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.ofNullable(player);
        }catch (NoResultException e){
            return Optional.empty();
        }
        catch (Exception e) {
            throw new DatabaseException("Problem with database with finding by name");
        }
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }
}