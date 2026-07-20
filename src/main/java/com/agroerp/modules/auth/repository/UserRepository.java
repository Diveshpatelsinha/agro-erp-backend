package com.agroerp.modules.auth.repository;

import com.agroerp.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User entity.
 *
 * JpaRepository gives us these methods for free:
 *  - save()
 *  - findById()
 *  - findAll()
 *  - deleteById()
 *  - count()
 *  - existsById()
 *
 * We add three custom finder methods below.
 * Spring Data JPA automatically generates the
 * SQL queries from the method names at runtime.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Used by UserDetailsService during login.
     *
     * Allows user to login with EITHER username OR email.
     *
     * Generated SQL:
     * SELECT * FROM users
     * WHERE username = ? OR email = ?
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Used during registration to check duplicate username.
     *
     * Generated SQL:
     * SELECT COUNT(*) > 0 FROM users WHERE username = ?
     */
    boolean existsByUsername(String username);

    /**
     * Used during registration to check duplicate email.
     *
     * Generated SQL:
     * SELECT COUNT(*) > 0 FROM users WHERE email = ?
     */
    boolean existsByEmail(String email);
}