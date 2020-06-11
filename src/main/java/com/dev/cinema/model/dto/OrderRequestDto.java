package com.dev.cinema.model.dto;

public class OrderRequestDto {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderRequestDto{" + "userId=" + userId + '}';
    }
}
