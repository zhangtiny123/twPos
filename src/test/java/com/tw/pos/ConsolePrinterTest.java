package com.tw.pos;

import com.tw.pos.models.Cart;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;
import com.tw.pos.processors.CartProcessor;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
* Created by taozhang on 1/5/15.
*/
public class ConsolePrinterTest {
    PrintStream out = mock(PrintStream.class);
    @Test
    public void should_output_the_right_format_to_console_when_call_the_function() throws IOException {
        Cart cart = mock(Cart.class);
        CartProcessor cartProcessor = mock(CartProcessor.class);
        List<Item> mockedItemList = CartItemListBuilder.getItemList()
                .with("ITEM000001", 40, 5)
                .with("ITEM000003", 50, 2)
                .with("ITEM000005", 60, 4)
                .build();

        when(cart.getCartList()).thenReturn(mockedItemList);
        when(cartProcessor.getCart()).thenReturn(cart);
        when(cartProcessor.getOriginTotal()).thenReturn(540.0);
        when(cartProcessor.getSubTotal(new Item(new Good("ITEM000001",40), 5))).thenReturn(120.0);
        when(cartProcessor.getSubTotal(new Item(new Good("ITEM000003",50), 2))).thenReturn(75.0);
        when(cartProcessor.getSubTotal(new Item(new Good("ITEM000005",60), 4))).thenReturn(216.0);
        when(cartProcessor.getTotal()).thenReturn(411.0);

        InOrder inOrder = inOrder(out);
        ConsolePrinter consolePrinter = new ConsolePrinter(cartProcessor, out);

        consolePrinter.outToConsole();

        inOrder.verify(out).println("购物明细\t\t\t数量\t\t单价\t\t\t小计");
//        inOrder.verify(out).println("ITEM000001\t\t5\t\t40.0\t\t120.0");
//        inOrder.verify(out).println("ITEM000003\t\t2\t\t50.0\t\t75.0");
//        inOrder.verify(out).println("ITEM000005\t\t4\t\t60.0\t\t216.0");
        inOrder.verify(out).println("总计金额\t\t\t优惠前\t优惠后\t\t优惠差价");
        inOrder.verify(out).println("411.0\t\t\t540.0\t411.0\t\t129.0");
    }
}
