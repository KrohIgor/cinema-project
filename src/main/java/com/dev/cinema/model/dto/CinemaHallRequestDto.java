package com.dev.cinema.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CinemaHallRequestDto {
    @Min(20)
    @Max(500)
    private int capacity;
    @NotNull
    @Size(min = 3, max = 100)
    private String description;

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
        return "CinemaHallRequestDto{" + "capacity=" + capacity
                + ", description='" + description + '\'' + '}';
    }
}
