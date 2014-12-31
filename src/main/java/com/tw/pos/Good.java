package com.tw.pos;

/**
 * Created by taozhang on 12/29/14.
 */
public class Good {
    private String barcode;
    private double price;
    private double discountedPrice;

    public Good(String barcode, double price) {
        this.barcode = barcode;
        this.price = price;
        this.discountedPrice = price;
    }

    public double getPrice() {
        return price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
