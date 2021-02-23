package com.example.webapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestJwt implements Serializable {
    private String username;
    private String password;

    public RequestJwt() {
    }

    public RequestJwt(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpty(){
        return username.length() == 0 && password.length() == 0;

    }

    @Override
    public String toString() {
        return "requestJWT{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
