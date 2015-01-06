package com.tw.pos;

import com.tw.pos.discounts.DiscountPromotionHandler;
import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by taozhang on 12/30/14.
 */
public class DiscountPromotionHandlerTest {
    FileContentReader fileContentReader = new FileContentReader();
    DiscountPromotionHandler discountPromotionHandler = new DiscountPromotionHandler(fileContentReader);

    @Test
    public void should_return_true_when_given_ITEM1_and_the_discount_promotion_list() throws IOException {
        Good good = new Good("ITEM000001", 40);

        assertThat(discountPromotionHandler.is_discounted(good)).isTrue();
    }

    @Test
    public void should_return_false_when_given_ITEM3_and_the_discount_promotion_list() throws IOException {
        Good good = new Good("ITEM000003", 50);

        assertThat(discountPromotionHandler.is_discounted(good)).isFalse();
    }

    @Test
    public void should_return_150_when_given_item1_with_5_count() throws IOException {
        Item item = new Item(new Good("ITEM000001", 40), 5);
        assertThat(discountPromotionHandler.discountFor(item)).isEqualTo(150.0);
    }
}
