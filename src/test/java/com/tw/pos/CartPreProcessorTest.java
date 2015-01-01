package com.tw.pos;

import com.tw.pos.door.FileContentReader;
import com.tw.pos.models.Item;
import com.tw.pos.processors.CartPreProcessor;
import com.tw.pos.processors.ItemListPreProcessor;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by taozhang on 12/27/14.
 */
public class CartPreProcessorTest {

    @Test
    public void should_return_counted_list_of_selected_products_when_given_the_read_cart_info() throws IOException {
        FileContentReader fileContentReader = new FileContentReader();
        ItemListPreProcessor itemListPreProcessor = new ItemListPreProcessor(fileContentReader);

        CartPreProcessor cartPreProcessor = new CartPreProcessor(fileContentReader, itemListPreProcessor);

        List<Item> processedCartList = cartPreProcessor.process_list();
        assertThat(processedCartList.size(), is(3));
    }
}
