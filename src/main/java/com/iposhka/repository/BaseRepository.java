package com.iposhka.repository;

import com.iposhka.exception.DatabaseException;
import com.iposhka.model.BaseEntity;
import com.iposhka.util.ConnectionManager;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>>
        implements Repository<K, E> {
    private final Class<E> entityClass;
    protected final SessionFactory sessionFactory = ConnectionManager.getSessionFactory();

    public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E save(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            throw new DatabaseException("Some problem with saving in database");
        }
    }

    @Override
    public void delete(K id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException("Some problem with deleting in database");
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException("Some problem with updating in database");
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            E e = session.get(entityClass, id);
            return Optional.ofNullable(e);
        } catch (Exception e) {
            throw new DatabaseException("Some problem with finding in database");
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(entityClass);
            criteria.from(entityClass);
            return session.createQuery(criteria)
                    .getResultList();
        }catch (Exception e) {
            throw new DatabaseException("Some problem with finding in database");
        }
}
}
