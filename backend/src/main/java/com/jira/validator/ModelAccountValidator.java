package com.jira.validator;


import com.jira.models.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Pattern;

public class ModelAccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account col=(Account) target;
        Map<String, String> unmodifiableMap = Map.of("name","[0-9A-Za-z_]","surname","[0-9A-Za-z_]","email","^[a-zA-Z0-9._%+-]{8,64}@[a-z]{2,6}[.][a-z]{2,4}$");
        for (Map.Entry<String,String> entry : unmodifiableMap.entrySet()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,entry.getKey() , "empty or has whitespaces");
            try {
                Field hand = Account.class.getDeclaredField(entry.getKey());
                hand.setAccessible(true);
                String kValue = (String) hand.get(target);
                hand.setAccessible(false);
                if(!Pattern.matches(entry.getValue(),kValue)) {
                    throw new ValidationException("problems with field "+entry.getKey());

                }
            } catch (NoSuchFieldException e) {
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