package com.messageattempt.springbootmessage.service;

import com.messageattempt.springbootmessage.entity.Message;

import java.util.List;

public interface MessageService {

    // create a message
    Message createMessage(Message message);

    // update a message
    Message editMessage(Message message);

    // delete a message
    void deleteMessage(Long id);

    // get messages between two users, sort by date
    List<Message> findBySenderIDAndReceiverIDOrderByDateSentAsc(Long senderID, Long receiverID);

}
