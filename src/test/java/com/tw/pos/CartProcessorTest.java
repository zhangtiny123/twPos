package com.tw.pos;

import com.tw.pos.discounts.DiscountPromotion;
import com.tw.pos.discounts.SecondHalfPromotion;
import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.models.Cart;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;
import com.tw.pos.processors.CartProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by taozhang on 1/9/15.
 */
public class CartProcessorTest {
    private Cart cart;
    private CartProcessor cartProcessor;
    private DiscountPromotion discountPromotion;
    private SecondHalfPromotion secondHalfPromotion;

    @Before
    public void setUp() throws IOException {
        cart = mock(Cart.class);
        FileContentReader fileContentReader = new FileContentReader();
        discountPromotion = new DiscountPromotion(fileContentReader);
        secondHalfPromotion = new SecondHalfPromotion(fileContentReader);
        List<Item> mockedCartList = CartItemListBuilder.getItemList()
                .with("ITEM000001", 40, 5)
                .with("ITEM000003", 50, 2)
                .with("ITEM000005", 60, 4)
                .build();
        when(cart.getCartList()).thenReturn(mockedCartList);
        cartProcessor = new CartProcessor(cart, discountPromotion, secondHalfPromotion);
    }

    @Test
    public void should_return_the_origin_total_payments_540_when_given_the_cart_list_with_getOriginTotal_called(){
        double originTotal = cartProcessor.getOriginTotal();

        assertThat(originTotal, is(540.0));
    }

    @Test
    public void should_return_120_when_given_the_cart_list_and_given_item3_with_getSubTotal_called() throws IOException {
        Item item = new Item(new Good("ITEM000001", 40), 5);
        double subTotalItem1 = cartProcessor.getSubTotal(item);

        assertThat(subTotalItem1, is(120.0));
    }

    @Test
    public void should_return_total_411_when_given_the_cart_list_with_getTotal_called() throws IOException {
        double total = cartProcessor.getTotal();

        assertThat(total, is(411.0));
    }
}
