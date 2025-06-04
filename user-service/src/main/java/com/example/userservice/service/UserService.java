package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.model.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.dto.CreateUserCommand;
import com.example.userservice.service.dto.GetOrdersResponse;
import com.example.userservice.service.dto.Order;
import com.example.userservice.service.dto.UserInfo;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderServiceClient orderServiceClient;
//    private final RestTemplate restTemplate;

    @Transactional
    public UserInfo createUser(CreateUserCommand command) {
        String userId = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        UserEntity userEntity = UserEntity.from(userId, encodedPassword, command);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return UserInfo.from(savedUserEntity);
    }

    @Transactional(readOnly = true)
    public UserInfo getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found userId: " + userId));

        /* Using restTemplate */
//        String orderUrl = String.format(env.getProperty("order-service.url"), userId);
//        ResponseEntity<GetOrdersResponse> getOrderResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null,
//            new ParameterizedTypeReference<GetOrdersResponse>() {});
//        GetOrdersResponse getOrderResponseBody  = getOrderResponse.getBody();

        /* Using feign client */
        GetOrdersResponse getOrdersResponse = orderServiceClient.getOrders(userId);
        List<Order> orders = getOrdersResponse.getOrders();

        return UserInfo.from(userEntity, orders);
    }

    @Transactional(readOnly = true)
    public UserInfo getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        List<Order> orders = new ArrayList<>(); // todo

        return UserInfo.from(userEntity, orders);
    }

    @Transactional(readOnly = true)
    public List<UserInfo> getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserInfo> userInfoList = new ArrayList<>();

        userEntityList.forEach(userEntity -> userInfoList.add(UserInfo.from(userEntity)));
        return userInfoList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new User(
            userEntity.getEmail(),
            userEntity.getPassword(),
            new ArrayList<>()
        );
    }
}
