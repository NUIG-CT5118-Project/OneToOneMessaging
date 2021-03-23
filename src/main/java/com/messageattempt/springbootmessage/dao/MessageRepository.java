package com.messageattempt.springbootmessage.dao;

import com.messageattempt.springbootmessage.entity.Message;
import com.messageattempt.springbootmessage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface MessageRepository extends JpaRepository<Message, Long> {

    // to get all messages between two users, sort by date from least recent to most recent
    @Query("SELECT m FROM Message m WHERE m.senderID IN (:senderID, :receiverID) AND m.receiverID IN (:senderID, :receiverID) ORDER BY m.dateSent ASC")
    List<Message> findBySenderIDAndReceiverIDOrderByDateSentAsc(@Param("senderID") Long senderID, @Param("receiverID") Long receiverID);

}
