package com.jira.validator;

import com.jira.pojo.JwtResponse;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class JwtResponseValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return JwtResponse.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "token", "Field token is empty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Field login is empty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "Field id is empty or has whitespaces");
        JwtResponse value=(JwtResponse) target;
        if(value.getLogin().length()>15 | value.getToken().length()>10 | Pattern.matches("[^0-9]",value.getId().toString())| Pattern.matches("[^0-9A-Za-z_]",value.getLogin())){
            try {
                throw new ValidationUserException("Incorrect data");
            } catch (ValidationUserException e) {
                e.printStackTrace();
            }
        }
    }
}
