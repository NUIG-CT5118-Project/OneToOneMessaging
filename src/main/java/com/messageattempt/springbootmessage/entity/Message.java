package com.messageattempt.springbootmessage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message_content", nullable = false, length = 10000)
    private String messageContent;

    @Column(name = "datetime_sent", nullable = false)
    @CreationTimestamp
    private Date dateSent;

    @Column(name = "sender_id", nullable = false)
    private Long senderID;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverID;

    public Message(String messageContent, Long senderID, Long receiverID) {
        this.messageContent = messageContent;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

}
