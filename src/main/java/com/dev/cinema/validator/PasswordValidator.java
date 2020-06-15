package com.dev.cinema.validator;

import com.dev.cinema.lib.PasswordValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value)
                .getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(value)
                .getPropertyValue(repeatPassword);
        if (passwordValue != null) {
            return passwordValue.equals(repeatPasswordValue);
        } else {
            return repeatPasswordValue == null;
        }
    }
}
