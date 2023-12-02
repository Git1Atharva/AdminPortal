package com.marici.portal.controller;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
