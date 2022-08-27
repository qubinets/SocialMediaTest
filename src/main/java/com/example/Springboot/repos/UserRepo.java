package com.example.Springboot.repos;

import com.example.Springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);



    User findByactivationCode(String code);

    List<User> findUsersByUsername(String username);

    User findUserByUsername(String username);
}
