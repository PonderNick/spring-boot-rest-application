package com.vector.restapplication.model;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;

/**
 * This class represents the User entity 
 */
@Entity
public class User {

    /**
     * The private Id of a user
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The private Email of a user
     */
    @NotBlank(message = "Email is required")
    private String email;
    
    /**
     * The private password of a user
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * The private First Name of a user
     */
    private String firstName;

    /**
     * The private Last Name of a user
     */
    private String lastName;

    /**
     * The default User constructor
     */
    public User() {
        this.id = 0L;
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
    }

    /**
     * The User constructor
     * @param id
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     */
    public User(Long id, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the User's Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return the User's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the User's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return the User's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return the User's last name
     */
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * @param id - the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param email = the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param password = the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param firstName = the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName = the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Used to check if the User is valid
     * @return a Boolean
     */
    public boolean isValid() {
        return this.id != null || this.email != null || this.password != null;
    }

    /**
     * Constructs a LinkedHashMap object called user that contains a key value pair of strings
     * @return a LinkedHashMap of key value pairs containing the Users email, passowrd, first name and last name
     */
    public LinkedHashMap<String, String> UserResponse() {
        LinkedHashMap<String, String> user = new LinkedHashMap<>();
        user.put("email", this.email);
        user.put("passowrd", this.password);
        user.put("firstName", this.firstName);
        user.put("lastName", this.lastName);

        return user;
    }

    /**
     * @return a String representing the User
     */
    public String toString() {
        return "User{" + "id=" + this.id + ", email='" + this.email + "', password='" + this.password + "', firstName='" + this.firstName + "', lastName='" + this.lastName + "'}";
    }

}
