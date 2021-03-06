package com.tw.pos;

import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.models.Good;
import com.tw.pos.processors.GoodListPreProcessor;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by taozhang on 12/29/14.
 */
public class GoodListPreProcessorTest {
    @Test
    public void should_return_a_list_of_goods_object_when_processed_the_read_item_list() throws IOException {
        FileContentReader fileContentReader = new FileContentReader();
        GoodListPreProcessor goodListPreProcessor = new GoodListPreProcessor(fileContentReader);
        List<Good> goodList = goodListPreProcessor.process();

        assertThat(goodList.size(), is(3));
        assertThat(goodList.get(0).getBarcode(), is("ITEM000001"));
        assertThat(goodList.get(0).getPrice(), is(40.0));

    }
}
