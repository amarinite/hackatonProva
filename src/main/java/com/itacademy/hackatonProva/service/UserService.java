package com.itacademy.hackatonProva.service;

import com.itacademy.hackatonProva.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);

    User updateUser(String id, User userDetails);

    void deleteUser(String id);
}
