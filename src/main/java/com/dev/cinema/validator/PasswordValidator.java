package com.dev.cinema.validator;

import com.dev.cinema.lib.PasswordValidation;
import com.dev.cinema.model.dto.UserRegistrationDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,
        UserRegistrationDto> {

    @Override
    public boolean isValid(UserRegistrationDto user, ConstraintValidatorContext context) {
        return Objects.equals(user.getPassword(), user.getRepeatPassword());
    }
}
