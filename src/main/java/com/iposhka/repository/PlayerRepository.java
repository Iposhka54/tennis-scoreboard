package com.iposhka.repository;

import com.iposhka.exception.DatabaseException;
import com.iposhka.model.Player;
import lombok.NoArgsConstructor;
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
            Player player = session.createQuery("from Player where name = :name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.ofNullable(player);
        }catch (Exception e) {
            throw new DatabaseException("Could not find player with name " + name);
        }
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }
}