package ru.itis.oris.oris_spring.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.oris.oris_spring.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllByIdInAndBirthDateBetween(Iterable<UUID> ids, LocalDate from, LocalDate to);
}
