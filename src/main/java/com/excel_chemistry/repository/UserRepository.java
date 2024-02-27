package com.excel_chemistry.repository;

import com.excel_chemistry.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
	User findByEmail(String email);
}
