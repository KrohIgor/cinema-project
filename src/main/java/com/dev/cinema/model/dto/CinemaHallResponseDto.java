package com.dev.cinema.model.dto;

public class CinemaHallResponseDto {

    private Long cinemaHallId;
    private int capacity;
    private String description;

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CinemaHallResponseDto{" + "cinemaHallId=" + cinemaHallId
                + ", capacity=" + capacity + ", description='" + description + '\'' + '}';
    }
}
