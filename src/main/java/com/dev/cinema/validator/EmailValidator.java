package com.dev.cinema.validator;

import com.dev.cinema.lib.EmailValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final String REGEX_EMAIL_VALIDATOR = "^.+@.*";

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches(REGEX_EMAIL_VALIDATOR);
    }
}
