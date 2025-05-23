package com.example.userservice.service;

import com.example.userservice.model.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.Order;
import com.example.userservice.service.dto.User;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(CreateUserCommand command) {
        String userId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        UserEntity userEntity = UserEntity.from(userId, encodedPassword, command);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return User.from(savedUserEntity);
    }

    @Transactional(readOnly = true)
    public User getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found userId: " + userId));

        List<Order> orders = new ArrayList<>(); // todo

        return User.from(userEntity, orders);
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<User> userList = new ArrayList<>();

        userEntityList.forEach(userEntity -> userList.add(User.from(userEntity)));
        return userList;
    }
}
