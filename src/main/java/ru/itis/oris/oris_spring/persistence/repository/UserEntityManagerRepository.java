package ru.itis.oris.oris_spring.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.itis.oris.oris_spring.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class UserEntityManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(UserEntity user) {
        entityManager.persist(user);
    }

    public Optional<UserEntity> findById(UUID id) {
        UserEntity entity = entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(entity);
    }

    public List<UserEntity> findAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
        return query.getResultList();
    }

    public UserEntity update(UserEntity user) {
        return entityManager.merge(user);
    }

    public void deleteById(UUID id) {
        UserEntity entity = entityManager.find(UserEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
