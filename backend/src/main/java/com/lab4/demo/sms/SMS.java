package com.lab4.demo.sms;

public class SMS {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SMS(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SMS{" +
                ", message='" + message + '\'' +
                '}';
    }
}