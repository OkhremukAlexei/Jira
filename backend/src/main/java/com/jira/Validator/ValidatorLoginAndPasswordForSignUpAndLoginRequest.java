package com.jira.Validator;

import com.jira.pojo.LoginRequest;
import com.jira.pojo.SignupRequest;
import com.jira.services.UserDetailsImpl;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidatorLoginAndPasswordForSignUpAndLoginRequest implements Validator {


    @Override
    public boolean supports(Class clazz) {
        return LoginRequest.class.equals(clazz)| SignupRequest.class.equals(clazz) | UserDetailsImpl.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login","login is empty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","password is empty or has whitespaces");

    }
}
