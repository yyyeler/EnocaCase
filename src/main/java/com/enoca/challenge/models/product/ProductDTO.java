package com.enoca.challenge.models.product;

public class ProductDTO {
    long productId;

    String productName;

    int productCount;

    double productPrice;

    double productTotalPrice;

    public ProductDTO(){}

    public ProductDTO(long productId, String productName, double productPrice, int productCount, double productTotalPrice)
    {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productTotalPrice = productTotalPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }
}
