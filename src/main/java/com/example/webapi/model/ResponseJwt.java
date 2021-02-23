package com.example.webapi.model;

import java.io.Serializable;

public class ResponseJwt implements Serializable {
    private String token;

    public ResponseJwt() {
    }

    public ResponseJwt(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResponseJwt{" +
                "token='" + token + '\'' +
                '}';
    }
}
