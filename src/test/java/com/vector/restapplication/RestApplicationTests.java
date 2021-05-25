package com.vector.restapplication;

import static org.assertj.core.api.Assertions.assertThat;
import com.vector.restapplication.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class RestApplicationTests { // Just call it RestApplicationTest, not Tests

	@Autowired UserController userController;

	@Test
	void contextLoads() throws Exception { // The throws Exception is not required
		assertThat(userController).isNotNull();
	}

}
