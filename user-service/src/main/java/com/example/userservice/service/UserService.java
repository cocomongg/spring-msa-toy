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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

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
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(
            userEntity.getEmail(),
            userEntity.getPassword(),
            new ArrayList<>()
        );
    }
}
