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
            log.info("Preloading " + repository.save(new User(1L, "test@test.com", "123456789", "Bilbo", "Baggins")));
            log.info("Preloading " + repository.save(new User(2L, "example@example.com", "987654321", "Frodo", "Baggins")));
        };
    }
    
}

