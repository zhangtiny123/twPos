package com.tw.pos;

import com.tw.pos.models.Good;
import com.tw.pos.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozhang on 1/5/15.
 */
public class CartItemListBuilder {
    private List<Item> builtItemList = new ArrayList<Item>();

    public static CartItemListBuilder getItemList() {

        return new CartItemListBuilder();
    }


    public CartItemListBuilder with(String barcode, int price, int count) {
        Good good = new Good(barcode, price);
        Item item1 = new Item(good, count);
        this.builtItemList.add(item1);
        return this;
    }

    public List<Item> build() {
        List<Item> resultList = new ArrayList<Item>();
        for (Item item : this.builtItemList) {
            resultList.add(item);
        }
        return resultList;
    }
}
