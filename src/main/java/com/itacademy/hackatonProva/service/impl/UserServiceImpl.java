package com.itacademy.hackatonProva.service.impl;

import com.itacademy.hackatonProva.exception.UserNotFoundException;
import com.itacademy.hackatonProva.model.User;
import com.itacademy.hackatonProva.repository.UserRepository;
import com.itacademy.hackatonProva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User updateUser(String id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }
}