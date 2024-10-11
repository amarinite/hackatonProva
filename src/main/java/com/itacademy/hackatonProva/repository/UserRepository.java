package com.itacademy.hackatonProva.repository;

import com.itacademy.hackatonProva.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}