package com.vector.restapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vector.restapplication.controller.UserController;
import com.vector.restapplication.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

	/**
	 * Autowire in MockMvc
	 */
	@Autowired
	private MockMvc mvc;

	/**
	 * Create a Mock implementation of the User class
	 */
	@MockBean
	private User user;

	/**
	 * Create a Mock implementation of the UserController controller
	 */
	@MockBean
	private UserController userController;

	/**
	 * Makes a post request to /user passing in a user object and expects a 201 response status.
	 * @throws Exception
	 */
	@Test
	void createUser() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
		.post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(new User(3L, "myemail@gmail.com", "password", "fname", "lname")));
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(201, result.getResponse().getStatus());
	}

	/**
	 * Makes a put request to /user passing "test@test.com" as a path variable and expects a 204 response status.
	 * @throws Exception
	 */
	@Test
	void updateUser() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
		.put("/user/test@test.com")
		.contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(new User(0L, "myemail@gmail.com", "password", "fname", "lname")));
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(204, result.getResponse().getStatus());
	}

	/**
	 * Makes a get request to /user passing "test@test.com" as a path variable and expects a 200 response status.
	 * @throws Exception
	 */
	@Test
	void getUser() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/user/test@test.com");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	/**
	 * Makes a delete request to /user passing "test@test.com" as a path variable and expects a 204 response status.
	 * @throws Exception
	 */
	@Test
	void deleteUser() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/user/test@test.com");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals(204, result.getResponse().getStatus());
	}

	/**
	 * Converts the given object to a string
	 * @param obj - Object to be converted to String
	 * @return String representation of the provided Object
	 */
	String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
 

