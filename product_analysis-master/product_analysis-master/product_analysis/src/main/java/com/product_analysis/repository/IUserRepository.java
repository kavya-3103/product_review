package com.product_analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.product_analysis.entity.User;




@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	 User findByUsernameAndPassword(String username, String password);
	 boolean existsByUsername(String username);
}