package com.vector.restapplication.repository;

import javax.transaction.Transactional;
import com.vector.restapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The UserRepository interface implements access to the JpaRepository CRUD operations
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}