package com.excel_chemistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.excel_chemistry.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
