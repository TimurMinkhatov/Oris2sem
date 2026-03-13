package ru.itis.oris.oris_spring.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.oris.oris_spring.persistence.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Profile("local")
@Repository
@RequiredArgsConstructor
public class JdbcUserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public void save(UserEntity user) {
        String sql = "INSERT INTO account (id, name, birth_date) VALUES (:id, :name, :birthDate)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("name", user.getName())
                .addValue("birthDate", user.getBirthDate());

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<UserEntity> getById(UUID uuid) {
        String sql = "SELECT * FROM account WHERE id = :id";

        try {
            UserEntity entity = namedJdbcTemplate.queryForObject(sql, Map.of("id", uuid), userMapper);
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "SELECT * FROM account";
        return namedJdbcTemplate.query(sql, userMapper);
    }

    @Override
    public void update(UserEntity user) {
        String sql = "UPDATE account SET name = :name, birth_date = :birthDate WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("birthDate", user.getBirthDate())
                .addValue("id", user.getId());

        namedJdbcTemplate.update(sql, params);
    }

    @Override
    public boolean deleteById(UUID uuid) {
        String sql = "DELETE FROM account WHERE id = :id";
        int rows = namedJdbcTemplate.update(sql, Map.of("id", uuid));
        return rows > 0;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM account";
        namedJdbcTemplate.update(sql, Map.of());
    }

    private final RowMapper<UserEntity> userMapper = (rs, rowNumber) -> {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.fromString(rs.getString("id")));
        entity.setName(rs.getString("name"));
        entity.setBirthDate(LocalDate.parse(rs.getString("birth_date")));
        return entity;
    };
}
