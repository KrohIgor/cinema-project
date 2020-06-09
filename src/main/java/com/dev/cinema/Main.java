package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        Movie movie1 = new Movie();
        movie1.setTitle("Troy");
        MovieService movieService = context.getBean(MovieService.class);
        movie1 = movieService.add(movie1);
        movie = movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(150);
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHall1 = cinemaHallService.add(cinemaHall1);
        cinemaHall = cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setCinemaHall(cinemaHall1);
        movieSession1.setMovie(movie1);
        movieSession1.setShowTime(LocalDateTime.of(2020, 5, 25, 20, 20));
        movieSession.setShowTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)));

        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession1);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(movie1.getId(), LocalDate.of(2020, 5, 25))
                .forEach(System.out::println);

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        authenticationService.register("bob@gmail.com", "1234");
        authenticationService.register("alice@gmail.com", "4321");
        User bob = null;
        User alice = null;
        try {
            bob = authenticationService.login("bob@gmail.com", "1234");
            alice = authenticationService.login("alice@gmail.com", "4321");
            System.out.println(bob);
            System.out.println(alice);
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

        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now());
        MovieSession selectedMovieSession = availableSessions.get(0);
        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(selectedMovieSession, bob);
        shoppingCartService.addSession(selectedMovieSession, bob);
        shoppingCartService.addSession(selectedMovieSession, alice);
        ShoppingCart bobShoppingCart = shoppingCartService.getByUser(bob);
        System.out.println(bobShoppingCart);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.completeOrder(bobShoppingCart.getTickets(), bob);
        orderService.getOrderHistory(bob).forEach(System.out::println);
        ShoppingCart afterCompleteOrderShoppingCart = shoppingCartService.getByUser(bob);
        System.out.println(afterCompleteOrderShoppingCart);

        shoppingCartService.addSession(selectedMovieSession, bob);
        shoppingCartService.addSession(selectedMovieSession, bob);
        bobShoppingCart = shoppingCartService.getByUser(bob);
        orderService.completeOrder(bobShoppingCart.getTickets(), bob);
        orderService.getOrderHistory(bob).forEach(System.out::println);
    }
}
