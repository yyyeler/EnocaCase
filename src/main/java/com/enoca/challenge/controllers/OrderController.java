package com.enoca.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.challenge.error.ErrorMessage;
import com.enoca.challenge.models.order.OrderDTO;
import com.enoca.challenge.services.OrderService;

@RestController
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @Autowired
    ErrorMessage errorMessage;

    @GetMapping("/api/v1/PlaceOrder/{customerId}")
    public ErrorMessage placeOrder(@PathVariable long customerId) throws Exception
    {
        
        if(orderService.placeOrder(customerId))
        {
            errorMessage.setAll("200", "Order is placed");
        }
        else
        {
            errorMessage.setAll("401", "Product Count is bigger than Product Stock");
        }
        
        return errorMessage;
    }

    @GetMapping("/api/v1/GetOrderForCode/{customerId}/{orderCode}")
    public OrderDTO getOrderForCode(@PathVariable long customerId, @PathVariable String orderCode) throws Exception
    {
        return orderService.getOrder(customerId, orderCode);
    }

    @GetMapping("/api/v1/GetOrderForCustomer/{customerId}")
    public List<OrderDTO> GetOrderForCustomer(@PathVariable long customerId) throws Exception
    {
        return orderService.getAllOrdersForCustomer(customerId);
    }
}
