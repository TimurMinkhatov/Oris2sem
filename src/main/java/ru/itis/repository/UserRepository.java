package ru.itis.repository;
import org.springframework.stereotype.Repository;
import ru.itis.entity.User;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<UUID, User> userMap = new HashMap<>();

    public void save(User user) {
        if (Objects.isNull(user.getId())) {
            user.setId(UUID.randomUUID());
            userMap.put(user.getId(), user);
        } else {
            Optional.ofNullable(userMap.get(user.getId()))
                    .map(existed -> {
                        existed.setName(user.getName());
                        existed.setBirthDate(user.getBirthDate());
                        return existed;
                    })
                    .orElse(userMap.put(user.getId(), user));
        }
    }

    public User get(UUID id) {
        return userMap.getOrDefault(id, null);
    }

    public boolean delete(UUID id) {
        return !Objects.isNull(userMap.remove(id));
    }
}