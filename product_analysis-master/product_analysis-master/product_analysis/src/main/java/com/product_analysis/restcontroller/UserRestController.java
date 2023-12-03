package com.product_analysis.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.product_analysis.dto.UserLogin;

//import com.product_analysis.bean.CustomerBean;

import com.product_analysis.entity.User;
import com.product_analysis.exception.EmptyInputException;
import com.product_analysis.exception.UserNotFoundException;
import com.product_analysis.service.UserService;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
    private UserService userService;

    
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getRegistrationById(@PathVariable Integer id) {
    	User user = userService.getById(id);
        return ResponseEntity.ok(user);
  }
    
    @GetMapping("/all")
    @Transactional
    public ResponseEntity<List<User>> getAllRegistrations() {
        List<User> users = userService.getAllRegistrations();
        return ResponseEntity.ok(users);
    }
    



    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
    	userService.saveUser(user);
        return "User created successfully!";
    }
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userService.getById(id);
        
        
      
        if (existingUser != null) {
        	 
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneno(updatedUser.getPhoneno());
            // Add more fields to update as needed

            
            userService.saveUser(existingUser); // Save the updated user

            String successMessage = "User updated successfully";
            return ResponseEntity.ok(successMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
   }
    
    

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userlogin) {
        String username = userlogin.getUsername();
        String password = userlogin.getPassword();

        if (userService.validateUser(username, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
//    @GetMapping("/logout")
//    public ResponseEntity<String> logout() {
//        // Perform any additional cleanup or logic needed for logout
//        SecurityContextHolder.clearContext(); // This will clear the security context
//
//        return ResponseEntity.ok("Logout successful");
//    }
    
}

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
//}

//oamcode
//    @PutMapping("/updateCustomer/{userId}")
//    public ResponseEntity<Customer> updateCustomerByUserId(@RequestBody Customer customer,
//                                                            @PathVariable Integer userId) {
//        Customer updatedCustomer = customerService.updateCustomer(customer, userId);
//        return ResponseEntity.ok(updatedCustomer);
//    }
//}
