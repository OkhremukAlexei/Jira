package com.jira.Validator;

public class ValidationException extends Throwable {

    public ValidationException(String s) {
        System.out.println(s);
    }
    public ValidationException(String s, String field){

    }
}
