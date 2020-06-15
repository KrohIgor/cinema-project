package com.dev.cinema.validator;

import com.dev.cinema.lib.PasswordValidation;
import com.dev.cinema.model.dto.UserRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,
        UserRegistrationDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(UserRegistrationDto user, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(user)
                .getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(user)
                .getPropertyValue(repeatPassword);
        return passwordValue.equals(repeatPasswordValue);
    }
}
