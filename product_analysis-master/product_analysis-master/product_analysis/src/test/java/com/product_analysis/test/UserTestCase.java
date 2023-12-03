package com.product_analysis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.product_analysis.dto.UserLogin;
import com.product_analysis.entity.User;
import com.product_analysis.service.UserService;

@SpringBootTest
public class UserTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testGetById() {
        // Assuming there is a user with ID 1 in the database
        User user = userService.getById(1);
        assertNotNull(user);
    }

    @Test
    public void testGetAllRegistrations() {
        List<User> users = userService.getAllRegistrations();
        assertNotNull(users);
    }

    @Test
    @Transactional
    public void testCreateUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setEmail("test@example.com");
        user.setPhoneno("1234567890");

        userService.saveUser(user);

        // Check if the user is actually saved in the database
        assertTrue(userService.validateUser("testuser", "testpassword"));
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        // Assuming there is a user with ID 1 in the database
        User existingUser = userService.getById(1);
        assertNotNull(existingUser);

        User updatedUser = new User();
        updatedUser.setUsername("updateduser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPhoneno("9876543210");

        userService.saveUser(updatedUser);

        // Check if the user is actually updated in the database
        User updatedUserFromDB = userService.getById(1);
        assertEquals("updateduser", updatedUserFromDB.getUsername());
        assertEquals("updated@example.com", updatedUserFromDB.getEmail());
        assertEquals("9876543210", updatedUserFromDB.getPhoneno());
    }

    @Test
    public void testLogin() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("testuser");
        userLogin.setPassword("testpassword");

        assertTrue(userService.validateUser(userLogin.getUsername(), userLogin.getPassword()));

        // Check with invalid credentials
        userLogin.setPassword("wrongpassword");
        assertFalse(userService.validateUser(userLogin.getUsername(), userLogin.getPassword()));
    }
}
