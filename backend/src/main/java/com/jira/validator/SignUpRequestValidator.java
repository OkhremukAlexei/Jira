package com.jira.validator;

import com.jira.pojo.LoginRequest;
import com.jira.pojo.SignupRequest;
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
public class SignUpRequestValidator implements Validator {

    private static final Marker IMPORTANT = MarkerFactory.getMarker("IMPORTANT");

    private static final String EMAIL_REGEX="\\\\w+([\\\\.-]?\\\\w+)*@\\\\w+([\\\\.-]?\\\\w+)*\\\\.\\\\w{2,4}";

    private static final String REGEX="[^0-9A-Za-z_]";

    private static final String FIELDS[]=new String[] {"name", "password","surname","login","email"};

    private static final Logger LOGGER= LoggerFactory.getLogger(SignUpRequestValidator.class);

    @Override
    public boolean supports(Class clazz) {
        return SignupRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
    SignupRequest value=(SignupRequest) target;
        LOGGER.info("Validation of LoginRequest data: (Login, Password, name, surname, email): "+ value.getLogin()+" "+value.getPassword()+" "+value.getName()+" "+value.getSurname()+" "+value.getEmail());
        for(String field : FIELDS){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "Field name is empty or has whitespaces");
        }
        if(value.getLogin().length()>15 | value.getPassword().length()>10 | Pattern.matches(REGEX,value.getPassword())| Pattern.matches(REGEX,value.getLogin()) | Pattern.matches(EMAIL_REGEX,value.getEmail())){
            try {
                throw new ValidationUserException();
            } catch (ValidationUserException e) {
                LOGGER.error(IMPORTANT,"Problems with data, incorrect data ",e);
                value.setPassword(" ");
                target =value;
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field name is empty or has whitespaces");
            }
        }
    }
}
