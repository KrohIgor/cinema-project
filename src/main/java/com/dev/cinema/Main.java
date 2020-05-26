package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
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

        AuthenticationService authenticationService = (AuthenticationService)
                INJECTOR.getInstance(AuthenticationService.class);
        authenticationService.register("bob@gmail.com", "1234");
        authenticationService.register("alice@gmail.com", "4321");
        User user = null;
        try {
            System.out.println(authenticationService.login("bob@gmail.com", "1234"));
            System.out.println(authenticationService.login("alice@gmail.com", "4321"));
            user = authenticationService.login("bob@gmail.com", "1234");
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }
        try {
            System.out.println(authenticationService.login("bob@gmail.com", "1"));
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }
        try {
            System.out.println(authenticationService.login("bob.alice@gmail.com", "1234"));
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }

        ShoppingCartService shoppingCartService = (ShoppingCartService)
                INJECTOR.getInstance(ShoppingCartService.class);
        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now());
        MovieSession selectedMovieSession = availableSessions.get(0);
        shoppingCartService.addSession(selectedMovieSession, user);
        ShoppingCart userShoppingCart = shoppingCartService.getByUser(user);
        System.out.println(userShoppingCart);
    }
}
