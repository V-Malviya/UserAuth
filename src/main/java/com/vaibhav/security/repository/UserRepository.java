package com.vaibhav.security.repository;

import com.vaibhav.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User save(User user);
    boolean existsUserByEmail(String email);
    boolean existsUserByEmailAndAndPassword(String email, String password);
    User findUserByEmailAndPassword(String user,String password);
}
