package com.enoca.challenge.models.cart;

import java.util.List;

import com.enoca.challenge.models.product.ProductDTO;

public class CartDetailedDTO {
    String orderCode;
    List<ProductDTO> productList;
    double cartTotalPrice;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    public double getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public CartDetailedDTO(List<ProductDTO> productList, double cartTotalPrice)
    {
        this.productList = productList;
        this.cartTotalPrice = cartTotalPrice;
    }
}
