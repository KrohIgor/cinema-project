package com.dev.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {

    private Long shoppingCartId;
    private List<Long> ticketIds;
    private Long userId;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShoppingCartResponseDto{" + "shoppingCartId=" + shoppingCartId
                + ", ticketIds=" + ticketIds + ", userId=" + userId + '}';
    }
}
