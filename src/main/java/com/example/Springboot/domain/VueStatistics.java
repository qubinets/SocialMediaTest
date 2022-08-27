package com.example.Springboot.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


//this class will collect all statistics about user and his messages.
public class VueStatistics {

    private int totalLikes;
    private int totalSubscribers;
    private int totalSubscriptions;
    private Message mostPopularMessage;

    private int mostPopularMessageLikes;
    private int totalMessages;

    @JsonIgnore
    private List<Message> messages;
    private UserInfo userinfo;

    public VueStatistics() {
    }

    public VueStatistics(int totalLikes, int totalSubscribers, Message mostPopularMessage, int totalMessages, UserInfo userinfo,int totalSubscriptions, int mostPopularMessageLikes) {
        this.totalLikes = totalLikes;
        this.totalSubscribers = totalSubscribers;
        this.mostPopularMessage = mostPopularMessage;
        this.totalMessages = totalMessages;
        this.totalSubscriptions = totalSubscriptions;
        this.userinfo = userinfo;
        this.mostPopularMessageLikes = mostPopularMessageLikes;
    }

    public int getTotalLikes() {

        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalSubscribers() {
        return totalSubscribers;
    }

    public void setTotalSubscribers(int totalSubscribers) {
        this.totalSubscribers = totalSubscribers;
    }

    public Message getMostPopularMessage() {
        return mostPopularMessage;
    }

    public void setMostPopularMessage(Message mostPopularMessage) {


        this.mostPopularMessage = mostPopularMessage;
    }

    public int getTotalMessages()
    {

        return totalMessages;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    public int getMostPopularMessageLikes() {
        return mostPopularMessageLikes;
    }

    public void setMostPopularMessageLikes(int mostPopularMessageLikes) {
        this.mostPopularMessageLikes = mostPopularMessageLikes;
    }

    public List<Message> getMessages()
    {


        return messages;
    }

    public void setMessages(List<Message> messages) {


        this.messages = messages;
    }

    public int getTotalSubscriptions() {
        return totalSubscriptions;
    }

    public void setTotalSubscriptions(int totalSubscriptions) {
        this.totalSubscriptions = totalSubscriptions;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "VueStatistics{" +
                "totalLikes=" + totalLikes +
                ", totalSubscribers=" + totalSubscribers +
                ", totalSubscriptions=" + totalSubscriptions +
                ", mostPopularMessage=" + mostPopularMessage +
                ", mostPopularMessageLikes=" + mostPopularMessageLikes +
                ", totalMessages=" + totalMessages +
                ", userinfo=" + userinfo +
                '}';
    }
}
