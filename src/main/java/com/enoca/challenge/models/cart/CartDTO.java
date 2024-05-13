package com.enoca.challenge.models.cart;


public class CartDTO {

   long productId;

   int productCount;

   double productPrices;

   

    public CartDTO(){}

    public CartDTO(long productId,int productCount,double productPrices)
    {
        this.productId = productId;
        this.productCount = productCount;
        this.productPrices = productPrices;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public double getProductPrices() {
        return productPrices;
    }
    
    public void setProductPrices(double productPrices) {
        this.productPrices = productPrices;
    }

}
