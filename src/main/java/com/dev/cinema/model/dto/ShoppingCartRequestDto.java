package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class ShoppingCartRequestDto {
    @NotNull
    private Long movieSessionId;
    @NotNull
    private Long userId;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShoppingCartRequestDto{" + "movieSessionId=" + movieSessionId
                + ", userId=" + userId + '}';
    }
}
