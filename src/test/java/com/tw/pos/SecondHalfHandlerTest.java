package com.tw.pos;

import com.tw.pos.discounts.SecondHalfHandler;
import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by taozhang on 12/30/14.
 */
public class SecondHalfHandlerTest {
    FileContentReader fileContentReader = new FileContentReader();
    SecondHalfHandler secondHalfHandler = new SecondHalfHandler(fileContentReader);

    @Test
    public void should_return_true_when_given_the_item3() throws IOException {
        Good good = new Good("ITEM000003", 50.0);
        assertThat(secondHalfHandler.isSecondHalf(good)).isTrue();
    }

    @Test
    public void should_return_false_when_given_the_item5() throws IOException {
        Good good = new Good("ITEM000005", 60.0);
        assertThat(secondHalfHandler.isSecondHalf(good)).isFalse();
    }

    @Test
    public void should_return_125_when_given_the_item3_with_count_3_and_the_promotion_list() throws IOException {
        Item item = new Item(new Good("ITEM000003", 50.0), 3);
        assertThat(secondHalfHandler.discountFor(item)).isEqualTo(125.0);
    }
}
