package com.messageattempt.springbootmessage.service;

import com.messageattempt.springbootmessage.dao.MessageRepository;
import com.messageattempt.springbootmessage.dao.UserRepository;
import com.messageattempt.springbootmessage.entity.Message;
import com.messageattempt.springbootmessage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;

    private UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    // create a message
    @Transactional
    public Message createMessage(Message message) {

        // find sender user and receiver user
        Optional<User> senderUser = userRepository.findById(message.getSenderID());
        Optional<User> receiverUser = userRepository.findById(message.getReceiverID());

        // if message is empty, do not create
        if (message.getMessageContent().length() == 0 ) {
            throw new IllegalStateException("Message cannot be empty.");
        }

        // if sender or receiver is empty, do not create
        if (senderUser.isEmpty() || receiverUser.isEmpty()) {
            throw new IllegalStateException("Sender and receiver must be registered users.");
        }


        // if sender and receiver are the same user, do not create
        if (senderUser.equals(receiverUser)) {
            throw new IllegalStateException("Sender cannot be the same as receiver.");
        }

        return messageRepository.save(message);

    }

    // edit a message
    @Transactional
    public Message editMessage(Message message) {

        // if message is empty, do not update
        if (message.getMessageContent().length() == 0 ) {
            throw new IllegalStateException("Message cannot be empty.");
        }

        // find message by id
        Optional<Message> messageOptional = messageRepository.findById(message.getId());

        // if message exists then update, else throw exception
        if (messageOptional.isPresent()) {
            Message messageUpdate = messageOptional.get();
            // only allow message content to be edited
            messageUpdate.setMessageContent(message.getMessageContent());
            messageRepository.save(messageUpdate);
            return messageUpdate;
        } else {
            throw new IllegalStateException("Message with id " + message.getId() + " does not exist. Cannot update.");
        }

    }

    // delete a message
    @Transactional
    public void deleteMessage(Long id) {

        // find message by id
        Optional<Message> messageOptional = messageRepository.findById(id);

        // if message exists then delete, else throw exception
        if (messageOptional.isPresent()) {
            messageRepository.delete(messageOptional.get());
        } else {
            throw new IllegalStateException("Message with id " + id + " does not exist. Cannot delete.");
        }

    }

    // get messages between two users, sort by date
    @Transactional
    public List<Message> findBySenderIDAndReceiverIDOrderByDateSentAsc(Long senderID, Long receiverID) {

        // find sender user and receiver user
        Optional<User> senderUser = userRepository.findById(senderID);
        Optional<User> receiverUser = userRepository.findById(receiverID);

        // if sender or receiver is empty, do not try get messages
        if (senderUser.isEmpty() || receiverUser.isEmpty()) {
            throw new IllegalStateException("Sender and receiver must be registered users.");
        }

        // if sender and receiver are the same user, do not try get messages
        if (senderUser.equals(receiverUser)) {
            throw new IllegalStateException("Sender cannot be the same as receiver.");
        }

        List<Message> messageResponse = messageRepository.findBySenderIDAndReceiverIDOrderByDateSentAsc(senderID, receiverID);

        return messageResponse;
    }

}
