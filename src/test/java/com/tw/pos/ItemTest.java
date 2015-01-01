package com.tw.pos;

import com.tw.pos.models.Good;
import com.tw.pos.models.Item;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by taozhang on 12/29/14.
 */
public class ItemTest {
    @Test
    public void should_return_120_when_given_a_good_with_price_40_and_its_count_is_3(){
        Good good = new Good("ITEM000006", 40);
        Item item = new Item(good, 3);

        assertThat(item.calculatePayments(), is(120.0));
    }
}
