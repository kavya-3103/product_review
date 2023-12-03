package com.product_analysis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import com.product_analysis.bean.CustomerBean;


import com.product_analysis.entity.User;
import com.product_analysis.exception.EmptyInputException;
import com.product_analysis.exception.InvalidInputException;
import com.product_analysis.exception.NoSuchElementException;


import jakarta.transaction.Transactional;
@Transactional
public interface UserService {
	  User getById(Integer id);

	    List<User> getAllRegistrations();

	    void saveUser(User user);
         
	    boolean validateUser(String username, String password);
	    
	    //void registerUser(User user);
	    
	   // void logout();
//		void deleteUser(Integer id);

	}

	