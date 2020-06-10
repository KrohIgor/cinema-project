package com.dev.cinema.model.dto;

import java.time.LocalDateTime;

public class MovieSessionResponseDto {

    private Long movieSessionId;
    private LocalDateTime showTime;
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    @Override
    public String toString() {
        return "MovieSessionResponseDto{" + "movieSessionId=" + movieSessionId
                + ", showTime=" + showTime + ", movieId=" + movieId
                + ", movieTitle='" + movieTitle + '\'' + ", cinemaHallId=" + cinemaHallId + '}';
    }
}
