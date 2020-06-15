package com.dev.cinema.controller;

import com.dev.cinema.mapper.ShoppingCartMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartRequestDto;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-carts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private MovieSessionService movieSessionService;
    private UserService userService;
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService, UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping(value = "/add-movie-session")
    public void addMovieSession(@RequestBody @Valid ShoppingCartRequestDto shoppingCartRequestDto,
                                Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        shoppingCartService.addSession(movieSessionService.get(shoppingCartRequestDto
                .getMovieSessionId()), user);
    }

    @GetMapping(value = "/by-user")
    private ShoppingCartResponseDto getByUser(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCartService
                .getByUser(user));
    }
}
