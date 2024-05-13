package com.enoca.challenge.models.order;

import java.util.List;

import com.enoca.challenge.models.product.ProductDTO;

public class OrderDTO {
    String orderCode;
    List<ProductDTO> productList;
    double orderTotalPrice;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public OrderDTO( String orderCode, List<ProductDTO> productList, double orderTotalPrice)
    {
        this.orderCode = orderCode;
        this.productList = productList;
        this.orderTotalPrice = orderTotalPrice;
    }

    public OrderDTO() {}
}
