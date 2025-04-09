package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.accountId = :accountId")
    boolean accountExists(@Param("accountId") Integer accountId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    int deleteMessageById(@Param("messageId") Integer messageId);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :newMessageText WHERE m.messageId = :messageId")
    int updateMessage(@Param("newMessageText") String newMessageText, @Param("messageId") Integer messageId);
    
    List<Message> findByPostedBy(Integer accountId);
    
}
