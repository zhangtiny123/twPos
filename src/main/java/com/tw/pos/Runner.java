package com.tw.pos;

import com.tw.pos.discounts.DiscountPromotion;
import com.tw.pos.discounts.SecondHalfPromotion;
import com.tw.pos.helper.FileSource;
import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.models.Cart;
import com.tw.pos.models.Store;
import com.tw.pos.processors.CartProcessor;
import com.tw.pos.processors.GoodListPreProcessor;

import java.io.IOException;

/**
 * Created by taozhang on 12/30/14.
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        FileContentReader fileContentReader = new FileContentReader();
        Cart cart = new Cart(new Store(new GoodListPreProcessor(fileContentReader)));
        cart.generateCartInfo(fileContentReader.readData(FileSource.CART_INFO_FILE_PATH));

        CartProcessor cartProcessor = new CartProcessor(cart, new DiscountPromotion(fileContentReader),
                new SecondHalfPromotion(fileContentReader));
        ConsolePrinter consolePrinter = new ConsolePrinter(cartProcessor, System.out);

        consolePrinter.outToConsole();
    }
}
