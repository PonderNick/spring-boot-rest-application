package com.vector.restapplication.controller;

import java.util.LinkedHashMap;

import javax.validation.Valid;
import com.vector.restapplication.model.User;
import com.vector.restapplication.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

/**
 * The UserController implements functionality to perform CRUD operations on the Users table
 */
@RestController
public class UserController {

    /**
     * The private UserRepository
     */
    private final UserRepository repository;

    /**
     * The UserController constructor
     * @param repository
     */
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Takes in post requests to the /user path and creates a new user
     * @param newUser - request body mapped to a User object
     * @throws Exception
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody User newUser) throws Exception {
        User request = repository.save(newUser);

        if (request == null || !request.isValid()) {
            throw new Exception("Failed to create the user.");
        }

    }

    /**
     * Takes in put requests to the /user/{email} path, finds a User based on the passed in email
     * and updates the User.
     * @param newUser - request body mapped to a User object
     * @param email - a User's email 
     * @throws Exception
     */
    @PutMapping("/user/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @RequestBody User newUser, @PathVariable String email) throws Exception {
        User getUserRequest = repository.findByEmail(email);

        if (getUserRequest == null || !getUserRequest.isValid()) {
            throw new Exception("Failed to retrieve a valid user for update.");
        }

        getUserRequest.setEmail(newUser.getEmail());
        getUserRequest.setPassword(newUser.getPassword());
        getUserRequest.setFirstName(newUser.getFirstName());
        getUserRequest.setLastName(newUser.getLastName());

        User updateUserRequest = repository.save(getUserRequest);

        if (updateUserRequest == null || !updateUserRequest.isValid()) {
            throw new Exception("Failed to update the user.");
        }
    }

    /**
     * Takes in get requests to the /user/{email} path and finds the User by the passed in email 
     * @param email - a User's email 
     * @return - a LinkedHashMap containing the retrevied users email, password, first name and last name
     * @throws Exception
     */
    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    public LinkedHashMap<String, String> getUser(@PathVariable String email) throws Exception {
        User request = repository.findByEmail(email);

        if (request == null || !request.isValid()) {
            throw new Exception("Failed to retreive a user.");
        }
        
        return request.UserResponse();
    }

    /**
     * Takes in delete requests to the /user/{email} path and deletes the user with the passed in email
     * @param email - a User's email 
     * @throws Exception
     */
    @DeleteMapping("/user/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String email) throws Exception {
        User getUserRequest = repository.findByEmail(email);

        if (getUserRequest == null || !getUserRequest.isValid()) {
            throw new Exception("Failed to retreive a user.");
        }

        repository.deleteById(getUserRequest.getId());
    }
    
}
