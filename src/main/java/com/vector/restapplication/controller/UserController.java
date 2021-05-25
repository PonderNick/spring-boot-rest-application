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
    // This is where we delegate everything to a Service class. So we would create UserService.

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
//        All this logic below should be delegated to a service class
        // Is this variable named correctly? "request"???
        User request = repository.save(newUser);

        // you're doing validation on the request, after you've saved it???
        if (request == null || !request.isValid()) {
            // You can create custom exceptions, and then you can return an http 401 "Bad content", instead of http 500.
            throw new Exception("Failed to create the user.");
        }

        // Whats up with the blank line there! :P
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

        // Why are you validating the retrieved user from the database? Surely if it retrieved a saved user, then you dont need to isValid
        if (getUserRequest == null || !getUserRequest.isValid()) {
            throw new Exception("Failed to retrieve a valid user for update.");
        }

        getUserRequest.setEmail(newUser.getEmail());
        getUserRequest.setPassword(newUser.getPassword());
        getUserRequest.setFirstName(newUser.getFirstName());
        getUserRequest.setLastName(newUser.getLastName());

        User updateUserRequest = repository.save(getUserRequest);

        // you should validate the user before saving the user!
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
        // Is this variable named correctly? "request"???
        User request = repository.findByEmail(email);

        // Do you really need to see if the "request" is valid???
        if (request == null || !request.isValid()) {
            throw new Exception("Failed to retreive a user.");
        }

        // no no no - yuck
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
        // Is this variable named correctly? "getUserRequest"???
        User getUserRequest = repository.findByEmail(email);

        // Do you really need to see if the "request" is valid???
        if (getUserRequest == null || !getUserRequest.isValid()) {
            throw new Exception("Failed to retreive a user.");
        }

        repository.deleteById(getUserRequest.getId());
    }
    
}
