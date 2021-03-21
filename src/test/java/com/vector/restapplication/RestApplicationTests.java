package com.vector.restapplication;

import static org.assertj.core.api.Assertions.assertThat;
import com.vector.restapplication.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class RestApplicationTests {

	@Autowired UserController userController;

	@Test
	void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

}
