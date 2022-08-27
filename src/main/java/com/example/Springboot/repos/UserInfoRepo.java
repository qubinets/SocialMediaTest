package com.example.Springboot.repos;

import com.example.Springboot.domain.User;
import com.example.Springboot.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {
    @Query("SELECT i from UserInfo i where i.userId.id=?1")
    UserInfo findByUserId(Long id);
}
