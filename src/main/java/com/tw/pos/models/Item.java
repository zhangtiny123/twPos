package com.tw.pos.models;

/**
 * Created by taozhang on 12/27/14.
 */
public class Item {
    private Good good;
    private int count;

    public Item(Good good, int count) {
        this.good = good;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Good getGood() {
        return good;
    }

    public void plusCount(int count) {
        this.count += count;
    }

    public double calculatePayments() {
        return this.good.getDiscountedPrice() * this.count;
    }
}
