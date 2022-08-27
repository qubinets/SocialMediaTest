package com.example.Springboot.repos;

import com.example.Springboot.domain.PrivateMessage;
import com.example.Springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrivateMessageRepo  extends JpaRepository<PrivateMessage,Long> {
    @Query("select p from PrivateMessage p where p.to = ?1 order by p.privateMessageId desc")
    List<PrivateMessage> findAllByTo(User user);
}
