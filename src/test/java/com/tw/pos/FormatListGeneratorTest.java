package com.tw.pos;

import com.tw.pos.discounts.DiscountPromotionHandler;
import com.tw.pos.discounts.SecondHalfHandler;
import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.models.Item;
import com.tw.pos.processors.CartPreProcessor;
import com.tw.pos.processors.ItemListPreProcessor;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by taozhang on 1/5/15.
 */
public class FormatListGeneratorTest {
    @Test
    public void should_return_generated_console_format_list_when_given_the_console_output_need() throws IOException {
        FileContentReader fileContentReader = new FileContentReader();
        CartPreProcessor cart = mock(CartPreProcessor.class);
        List<Item> mockedItemList = CartItemListBuilder.getItemList()
                .with("ITEM000001", 40, 5)
                .with("ITEM000003", 50, 2)
                .with("ITEM000005", 60, 4)
                .build();
        when(cart.process_list()).thenReturn(mockedItemList);

        DiscountPromotionHandler discount = new DiscountPromotionHandler(fileContentReader);
        SecondHalfHandler secondHalf = new SecondHalfHandler(fileContentReader);
        FormatListGenerator formatListGenerator = new FormatListGenerator(cart, discount, secondHalf);

    }
}
