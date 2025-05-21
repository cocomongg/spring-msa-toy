package com.example.userservice.service;

import com.example.userservice.model.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.User;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserCommand command) {
        UserEntity userEntity = UserEntity.from(UUID.randomUUID().toString(), command);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return User.from(savedUserEntity);
    }
}
