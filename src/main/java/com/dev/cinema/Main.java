package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Troy");
        movie1 = movieService.add(movie1);
        movie = movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService = (CinemaHallService)
                INJECTOR.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(150);
        cinemaHall1 = cinemaHallService.add(cinemaHall1);
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setCinemaHall(cinemaHall1);
        movieSession1.setMovie(movie1);
        movieSession1.setShowTime(LocalDateTime.of(2020,5,25,20,20));
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));
        MovieSessionService movieSessionService = (MovieSessionService)
                INJECTOR.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession1);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(movie1.getId(), LocalDate.of(2020,5,25))
                .forEach(System.out::println);
    }
}
