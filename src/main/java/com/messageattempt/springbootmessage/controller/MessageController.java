package com.messageattempt.springbootmessage.controller;

import com.messageattempt.springbootmessage.entity.Message;
import com.messageattempt.springbootmessage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) { this.messageService = messageService; }

    // to add message
    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) { return messageService.createMessage(message); }

    // to update message
    @PutMapping("/messages/{id}")
    public Message editMessage(@PathVariable("id") Long id, @RequestBody Message message) {
        message.setId(id);
        return messageService.editMessage(message);
    }

    // to delete message
    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable("id") Long id) { messageService.deleteMessage(id); }

    // to get messages between two users
    @RequestMapping(value = "messages/findBySenderIDAndReceiverIDOrderByDateSentAsc/{senderID}/{receiverID}",
    method = RequestMethod.GET)
    @ResponseBody
    public List<Message> findBySenderIDAndReceiverIDOrderByDateSentAsc(@PathVariable Long senderID, @PathVariable Long receiverID) {
        List<Message> messageResponse = (List<Message>) messageService.findBySenderIDAndReceiverIDOrderByDateSentAsc(senderID, receiverID);
        return messageResponse;
    }

}
