package com.jira.Validator;

import com.jira.models.Project;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Pattern;

public class ModelProjectValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project col= (Project) target;
        Map<String, String> unmodifiableMap = Map.of("name","[0-9A-Za-z_+-()?/*@#]","linkToGit","https://github[.]com/[a-zA-Z0-9_-]+/[a-zA-Z0-9_-]+");
        for (Map.Entry<String,String> entry : unmodifiableMap.entrySet()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,entry.getKey() , "empty or has whitespaces");
            try {
                Field hand = Project.class.getDeclaredField(entry.getKey());
                hand.setAccessible(true);
                String kValue=(String) hand.get(target);
                hand.setAccessible(false);
            if(!Pattern.matches(entry.getValue(),kValue)) {
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
