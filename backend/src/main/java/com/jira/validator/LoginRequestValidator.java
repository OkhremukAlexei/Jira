package com.jira.validator;

import com.jira.pojo.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import java.util.regex.Pattern;

@Component
public class LoginRequestValidator implements Validator {

    private static final Marker IMPORTANT = MarkerFactory.getMarker("IMPORTANT");

    private static final String REGEX="[^0-9A-Za-z_]";

    private static final Logger LOGGER= LoggerFactory.getLogger(LoginRequestValidator.class);

    @Override
    public boolean supports(Class clazz) {
        return LoginRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequest value=(LoginRequest) target;
        LOGGER.trace("Validation of LoginRequest data: (Login, Password) "+ value.getLogin()+"  "+value.getPassword());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Field name is empty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field password is empty or has whitespaces");
        if(value.getLogin().length()>15 | value.getPassword().length()>10 | Pattern.matches(REGEX,value.getPassword())| Pattern.matches(REGEX,value.getLogin())){
            try {
                throw new ValidationUserException("Incorrect data");
            } catch (ValidationUserException e) {
                LOGGER.error(IMPORTANT,"Problems with data, incorrect data ",e);
                value.setPassword(" ");
                target =value;
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field name is empty or has whitespaces");

            }
        }
    }
}
