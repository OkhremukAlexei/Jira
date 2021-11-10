package com.jira.Validator;

import com.jira.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Pattern;

public class ModelUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User col= (User) target;
        Map<String, String> unmodifiableMap = Map.of("login","[0-9A-Za-z_]","password","[^0-9A-Za-z_]");
        for (Map.Entry<String,String> entry : unmodifiableMap.entrySet()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,entry.getKey() , "empty or has whitespaces");
            try {
                Field hand = User.class.getDeclaredField(entry.getKey());
                hand.setAccessible(true);
                String kValue=(String) hand.get(target);
                hand.setAccessible(false);
                if(!Pattern.matches(entry.getValue(),kValue) | kValue.length()>15) {
                    throw new ValidationException("problems with field "+entry.getKey());
                }
            }  catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (ValidationException e) {
                e.printStackTrace();
            }
        }
    }
}
