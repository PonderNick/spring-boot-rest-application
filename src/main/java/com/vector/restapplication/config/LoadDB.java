package com.vector.restapplication.config;

import com.vector.restapplication.model.User;
import com.vector.restapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The LoadDB class is used to pre-populate the Users table.
 */
@Configuration
public class LoadDB {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);
    
    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        
        return args -> {
            // Why do you have to preload the database? It's more secure if you don't. But anyway, never have a logging line do actual logic
//            final User savedUser = repository.save(new User(1L, "test@test.com", "123456789", "Bilbo", "Baggins"));
//            log.info("Preloading " + savedUser);

            // But then if you're calling it twice, create it a separate method
//            saveUser(repository, 1L, "test@test.com", "123456789", "Bilbo", "Baggins");
//            saveUser(repository, 2L, "example@example.com", "987654321", "Frodo", "Baggins");
            // you can see from this, that its much easier to read what is happening, and if you want to change add any extra logging, you just do it in the one method "saveUser"

            log.info("Preloading " + repository.save(new User(1L, "test@test.com", "123456789", "Bilbo", "Baggins")));
            log.info("Preloading " + repository.save(new User(2L, "example@example.com", "987654321", "Frodo", "Baggins")));
        };
    }

//    private void saveUser(UserRepository repository, long id, String email, String password, String firstName, String lastName) {
//        final User user = new User(id, email, password, firstName, lastName);
//        final User savedUser = repository.save(user);
//        log.info("Preloading " + savedUser);
//    }
}

