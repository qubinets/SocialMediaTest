package com.example.Springboot.repos;

import com.example.Springboot.domain.Message;
import com.example.Springboot.domain.User;
import com.example.Springboot.domain.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Set;


public interface MessageRepo extends CrudRepository<Message, Long> {


    public Set<Message> findAllByTextContains(String text);
    @Query("select  m " +
            "from Message m left join m.likes WHERE :user member m.author.subscribers OR m.author = :user " +
            "group by m"+
            " order by m.id desc")

    public Set<Message> findAllByAuthorSubscribersContains(@Param("user") User user);

    @Query("select new com.example.Springboot.domain.dto.MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "group by m")
    Page<MessageDto> findAll(Pageable pageable, @Param("user") User user);

    @Query("select new com.example.Springboot.domain.dto.MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.tag = :tag " +
            "group by m")
    Page<MessageDto> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query("select new com.example.Springboot.domain.dto.MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.author = :author " +
            "group by m")
    Page<MessageDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);

    @Query("Select m from Message m where m.author =?1")
    List<Message> findByUser(@Param("author") User author);


}
