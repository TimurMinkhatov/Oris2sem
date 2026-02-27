package ru.itis.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.entity.User;
import ru.itis.repository.UserRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    @Value("${logging.enabled}")
    private Boolean isLoggingEnabled;

    public void save(String name, LocalDate birthDate) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }

        User user = User.builder()
                .name(name)
                .birthDate(birthDate)
                .build();

        repository.save(user);

        if (isLoggingEnabled) {
            System.out.println("new user saved");
        }
    }

    public ru.itis.entity.User get(UUID id) {
        return repository.get(id);
    }
}