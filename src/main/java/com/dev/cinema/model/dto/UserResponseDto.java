package com.dev.cinema.model.dto;

public class UserResponseDto {

    private Long userId;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" + "userId=" + userId + ", email='" + email + '\'' + '}';
    }
}
