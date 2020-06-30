package com.dev.cinema.controller;

import com.dev.cinema.mapper.OrderMapper;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService,
                           UserService userService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public OrderResponseDto completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        User user = userService.get(orderRequestDto.getUserId());
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        return orderMapper.getOrderResponseDto(orderService.completeOrder(tickets, user));
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::getOrderResponseDto)
                .collect(Collectors.toList());
    }
}
