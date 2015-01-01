package com.tw.pos;

import com.tw.pos.models.Good;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by taozhang on 12/29/14.
 */
public class GoodTest {
    @Test
    public void should_return_the_good_price_and_barcode_when_call_the_get_method_of_good(){
        Good good = new Good("ITEM000007", 98.0);

        assertThat(good.getBarcode(), is("ITEM000007"));
        assertThat(good.getPrice(), is(98.0));
    }
}
