package com.tw.pos.processors;

import com.tw.pos.discounts.DiscountPromotion;
import com.tw.pos.discounts.SecondHalfPromotion;
import com.tw.pos.models.Cart;
import com.tw.pos.models.Item;

import java.io.IOException;

/**
 * Created by taozhang on 12/27/14.
 */
public class CartProcessor {
    private Cart cart;
    private DiscountPromotion dpHandler;
    private SecondHalfPromotion shHandler;

    public CartProcessor(Cart cart, DiscountPromotion dpHandler, SecondHalfPromotion shHandler) {
        this.cart = cart;
        this.dpHandler = dpHandler;
        this.shHandler = shHandler;
    }

    public double getOriginTotal() {
        double originTotal = 0;
        for (Item item : cart.getCartList()) {
            originTotal += item.getGood().getPrice() * item.getCount();
        }
        return originTotal;
    }

    public double getSubTotal(Item item) throws IOException {
        double subTotal = 0;
        Boolean discounted = dpHandler.is_discounted(item.getGood());
        Boolean secondHalf_discounted = shHandler.isSecondHalf(item.getGood());
        if (discounted) {
            subTotal = dpHandler.discountFor(item);
        }
        if (secondHalf_discounted) {
            subTotal = shHandler.discountFor(item);
        }
        if (!(discounted || secondHalf_discounted)) {
            subTotal = item.calculatePayments();
        }
        return subTotal;
    }

    public double getTotal() throws IOException {
        double total = 0;
        for (Item item : cart.getCartList()) {
            total += getSubTotal(item);
        }
        return total;
    }

    public Cart getCart() {
        return cart;
    }
}
