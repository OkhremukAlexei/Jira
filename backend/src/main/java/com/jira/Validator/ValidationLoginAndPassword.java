package com.jira.Validator;

import com.jira.pojo.LoginRequest;
import com.jira.pojo.SignupRequest;
import com.jira.services.UserDetailsImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class ValidationLoginAndPassword implements Validator {


    private String value;

    public void SetValue(String value) {
        this.value = value;
    }


    @Override
    public boolean supports(Class clazz) {
        return LoginRequest.class.equals(clazz)| SignupRequest.class.equals(clazz) | UserDetailsImpl.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login","login is empty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password is empty or has whitespaces");
        if (Pattern.matches("[^0-9A-Za-z_]",value) | value.length()>20){
            throw new IllegalArgumentException("Login or password is not correct.");
        }
        }


    }

