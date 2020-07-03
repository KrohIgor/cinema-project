package com.dev.cinema.model.dto;

import com.dev.cinema.lib.EmailValidation;
import com.dev.cinema.lib.PasswordValidation;
import javax.validation.constraints.NotNull;

@PasswordValidation
public class UserRegistrationDto {
    @NotNull
    @EmailValidation
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" + "email='" + email + '\''
                + ", password='" + password + '\'' + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }
}
