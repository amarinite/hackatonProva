package com.itacademy.hackatonProva.repository;

import com.itacademy.hackatonProva.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
}