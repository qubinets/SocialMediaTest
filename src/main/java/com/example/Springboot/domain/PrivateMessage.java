package com.example.Springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_private_message")
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "private_message_id")
    private Long privateMessageId;
    @NotBlank(message = "Subject cannot be blank")
    @Column(name = "message_subject")
    private String messageSubject;

    @NotBlank(message = "Message cannot be blank")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    @Column(name = "message_body")
    private String messageBody;

    @JsonIncludeProperties(value = {"id","username"})
    @ManyToOne
    @JoinColumn(name = "message_from")
    private User from;


    @JsonIncludeProperties(value = {"id","username"})
    @ManyToOne
    @JoinColumn(name = "message_to")
    private User to;

    public PrivateMessage() {
    }

    public PrivateMessage(String messageSubject, String messageBody, User from, User to) {

        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.from = from;
        this.to = to;
    }

    public PrivateMessage(String messageSubject, String messageBody, User to) {
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.to = to;
    }
    public PrivateMessage(String messageSubject, String messageBody) {
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;

    }


    public Long getPrivateMessageId() {
        return privateMessageId;
    }

    public void setPrivateMessageId(Long privateMessageId) {
        this.privateMessageId = privateMessageId;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }
}
