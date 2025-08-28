package com.cropwise.cw_system.services;

import com.cropwise.cw_system.dtos.user.UserMapper;
import com.cropwise.cw_system.dtos.user.UserRequest;
import com.cropwise.cw_system.dtos.user.UserResponse;
import com.cropwise.cw_system.models.User;
import com.cropwise.cw_system.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
       List<User> users = userRepository.findAll();
       return users
               .stream()
               .map(user -> UserMapper.entityToDto(user))
               .toList();
    }

    public UserResponse createUser(UserRequest userRequest) {
        User newUser = UserMapper.dtoToEntity(userRequest);
        User savedUser = userRepository.save(newUser);
        return UserMapper.entityToDto(savedUser);
    }

    public UserResponse getUserById (Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return UserMapper.entityToDto(user);
    }

    public UserResponse updateUser (Long id, UserRequest userRequest) {
        User actualUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id" + id));

        actualUser.setName(userRequest.name());
        actualUser.setEmail(userRequest.email());
        actualUser.setPassword(userRequest.password());

        User updateUser = userRepository.save(actualUser);
        return UserMapper.entityToDto(updateUser);
    }

    public void deleteUser (Long id){
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id" + id));
        userRepository.delete(currentUser);

    }


}
