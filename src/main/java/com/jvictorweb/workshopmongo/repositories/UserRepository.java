package com.jvictorweb.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jvictorweb.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
