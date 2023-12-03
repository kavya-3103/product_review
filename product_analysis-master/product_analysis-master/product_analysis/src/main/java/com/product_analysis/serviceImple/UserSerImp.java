package com.product_analysis.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.product_analysis.entity.User;
import com.product_analysis.exception.DuplicateUsernameException;
import com.product_analysis.exception.EmptyInputException;
import com.product_analysis.exception.UserNotFoundException;
import com.product_analysis.repository.IUserRepository;
import com.product_analysis.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserSerImp implements UserService {
	
	@Autowired
    private IUserRepository userRepository;

 

   //@Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
	
	

    public List<User> getAllRegistrations() {
        return userRepository.findAll();
    }
    //@Override
    @Transactional
    public void saveUser(User user) {
    	userRepository.save(user);
}
    

    
    //@Override
    public boolean validateUser(String username, String password) {
        // Basic validation logic (you may replace it with your authentication mechanism)
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }



	
//	@Override
//	public void logout() {
//		// TODO Auto-generated method stub
//		
//	}

    
    
//    @Override
//    public void deleteUser(Integer id) {
//    	userRepository.deleteById(id);
//    }
}
