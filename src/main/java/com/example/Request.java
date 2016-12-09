package com.example;

/**
 * Created by gimhani on 11/25/16.
 */
public class Request {
    private String message;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    private String access_token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
