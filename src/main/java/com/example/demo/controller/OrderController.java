package com.example.demo.controller;

import com.example.demo.modle.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders/parkingLots/{id}")
    public Order addOrder(@PathVariable("id")Long id, @RequestBody Order order){
        return orderService.addOrder(id,order);
    }

    @PatchMapping("/orders/{id}")
    public Order updateStatus(@PathVariable Long id){
       return orderService.updateStatus(id);
    }
}
