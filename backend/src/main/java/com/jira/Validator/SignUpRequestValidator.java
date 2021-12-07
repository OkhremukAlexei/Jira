package com.jira.validator;

import com.jira.pojo.SignupRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class SignUpRequestValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return SignupRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Field name is empty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field password is empty or has whitespaces");
        SignupRequest value=(SignupRequest) target;
        if(value.getLogin().length()>15 | value.getPassword().length()>10 | Pattern.matches("[^0-9A-Za-z_]",value.getPassword())| Pattern.matches("[^0-9A-Za-z_]",value.getLogin())){
            try {
                throw new ValidationUserException("Incorrect data");
            } catch (ValidationUserException e) {
                e.printStackTrace();
            }
        }
    }
}
