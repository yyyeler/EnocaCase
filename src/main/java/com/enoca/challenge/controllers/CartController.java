package com.enoca.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.challenge.error.ErrorMessage;
import com.enoca.challenge.models.cart.CartDTO;
import com.enoca.challenge.models.cart.CartDetailedDTO;
import com.enoca.challenge.services.CartService;
import com.enoca.challenge.services.CustomerService;
import com.enoca.challenge.services.ProductService;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ErrorMessage errorMessage;

    @GetMapping("/api/v1/GetCart/{customerId}")
    public CartDetailedDTO getCart(@PathVariable long customerId)
    {
        return cartService.getCart(customerId);
    }

    @PostMapping("/api/v1/AddProductToCart/{customerId}")
    public ErrorMessage addProductToCart(@PathVariable long customerId, @RequestBody CartDTO tempCart) throws Exception
    {
        if(cartService.addCart(tempCart,customerId))
        {
            errorMessage.setAll("200","Product is added to the cart");
        }
        else
        {
            errorMessage.setAll("401","Product Count is bigger than Product Stock");
        }

        return errorMessage;
    }

    @DeleteMapping("/api/v1/RemoveProductFromCart/{customerId}/{productId}")
    public ErrorMessage RemoveProductFromCart(@PathVariable long customerId, @PathVariable long productId)
    {
        cartService.removeProductFromCart(customerId, productId);
        errorMessage.setAll("200","Product is removed from the cart");
    
        return errorMessage;
    }

    @DeleteMapping("/api/v1/EmptyCart/{customerId}")
    public ErrorMessage EmptyCart(@PathVariable long customerId)
    {
        cartService.removeCart(customerId);
        errorMessage.setAll("200","Cart is got empty");
        
        return errorMessage;
    }

    @PutMapping("/api/v1/UpdateCart/{customerId}")
    public ErrorMessage UpdateCart(@PathVariable long customerId, @RequestBody List<CartDTO> list) throws Exception
    {
        if(cartService.updateCart(customerId, list))
        {
            errorMessage.setAll("200", "Cart is updated");
        }
        else
        {
            errorMessage.setAll("401","Product Count is bigger than Product Stock");
        }
    
        return errorMessage;
    }
    
}
