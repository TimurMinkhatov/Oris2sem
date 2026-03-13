package ru.itis.oris.oris_spring.persistence.repository;

import ru.itis.oris.oris_spring.persistence.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
