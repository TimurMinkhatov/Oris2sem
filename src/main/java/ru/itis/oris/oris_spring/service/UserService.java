package ru.itis.oris.oris_spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.oris.oris_spring.persistence.entity.UserEntity;
import ru.itis.oris.oris_spring.persistence.entity.UserRole;
import ru.itis.oris.oris_spring.persistence.entity.UserStatus;
import ru.itis.oris.oris_spring.persistence.repository.UserRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void register(String email, String password, String name) {
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .birthDate(LocalDate.now())
                .status(UserStatus.REGISTERED)
                .role(UserRole.USER)
                .build();

        repository.save(user);
    }

    public UserEntity get(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
