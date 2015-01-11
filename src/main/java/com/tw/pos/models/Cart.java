package com.tw.pos.models;

import com.tw.pos.processors.GoodListPreProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozhang on 1/8/15.
 */
public class Cart {
    private List<Item> cartList = new ArrayList<Item>();
    private Store store;

    public Cart(Store store) throws IOException {
        this.store = store;
    }

    public void generateCartInfo(List<String> readFromFileCartList) throws IOException {
        for (String temp : readFromFileCartList) {
            addItem(temp);
        }
    }

    public void addItem(String temp) {
        int position = getPositionOf(temp);
        if (position != -1) {
            this.cartList.get(position).plusCount(getItemCount(temp));
        }
        else {
            String barcode = getBarcode(temp);
            double singlePrice = getPriceByBarcode(barcode);
            int goodCount = getItemCount(temp);

            this.cartList.add(new Item(new Good(barcode, singlePrice), goodCount));
        }
    }

    private double getPriceByBarcode(String productCode) {
        for (Good good : this.store.getGoodList()) {
            if (good.getBarcode().equals(productCode)) {
                return good.getPrice();
            }
        }
        return 0;
    }

    private int getPositionOf(String stringCartItem) {
        int i = 0;
        for (Item item1 : cartList) {
            if (item1.getGood().getBarcode().equals(getBarcode(stringCartItem))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private String getBarcode(String stringCartItem) {
        return stringCartItem.split("-")[0];
    }

    private int getItemCount(String stringCartItem) {
        if (!stringCartItem.contains("-")) {
            return 1;
        }
        return Integer.parseInt(stringCartItem.split("-")[1]);
    }

    public List<Item> getCartList() {
        return cartList;
    }
}
