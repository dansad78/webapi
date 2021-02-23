package com.example.webapi.entity;

public class Message {
    String info;

    public Message(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message{" +
                "info='" + info + '\'' +
                '}';
    }
}
