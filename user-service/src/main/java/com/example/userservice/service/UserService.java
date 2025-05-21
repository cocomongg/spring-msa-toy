package com.example.userservice.service;

import com.example.userservice.model.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.User;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserCommand command) {
        String userId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        UserEntity userEntity = UserEntity.from(userId, encodedPassword, command);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return User.from(savedUserEntity);
    }
}
