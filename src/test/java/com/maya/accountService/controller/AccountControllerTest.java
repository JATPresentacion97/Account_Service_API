package com.maya.accountService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maya.accountService.model.Account;
import com.maya.accountService.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll(); // Clean up before each test
    }

    // Test case for POST /accounts
    @Test
    public void testCreateAccountSuccess() throws Exception {
        Account account = new Account();
        account.setUsername("user1");
        account.setPassword("password123");
        account.setFullname("John Doe");

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.fullname").value("John Doe"))
                .andExpect(jsonPath("$.password").exists())  // Make sure password hash is present
                .andExpect(jsonPath("$.id").exists());
    }

    // Test case for POST /accounts with invalid data (username too short)
    @Test
    public void testCreateAccountInvalidUsername() throws Exception {
        Account account = new Account();
        account.setUsername("u");  // Invalid username (too short)
        account.setPassword("password123");
        account.setFullname("John Doe");

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ACC0001"))  // Error code
                .andExpect(jsonPath("$.message").value("Invalid username"));  // Updated expected message
    }


    // Test case for GET /accounts/:username
    @Test
    public void testGetAccountSuccess() throws Exception {
        // Create an account in the repository
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setUsername("user1");
        account.setPassword("password123");
        account.setFullname("John Doe");
        accountRepository.save(account);

        mockMvc.perform(get("/accounts/{username}", "user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.fullname").value("John Doe"));
    }

    // Test case for GET /accounts/:username where the user doesn't exist
    @Test
    public void testGetAccountNotFound() throws Exception {
        mockMvc.perform(get("/accounts/{id}", 999)  // Assuming 999 is a non-existent account ID
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())  // HTTP 404 status
                .andExpect(jsonPath("$.code").value("ACC0004"))  // Error code
                .andExpect(jsonPath("$.message").value("User not found"));  // Update to actual message
    }


}
