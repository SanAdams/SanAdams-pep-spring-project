package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.accountId = :accountId")
    boolean accountExists(@Param("accountId") Integer accountId);
}
