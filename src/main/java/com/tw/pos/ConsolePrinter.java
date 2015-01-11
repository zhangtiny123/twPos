package com.tw.pos;

import com.tw.pos.models.Item;
import com.tw.pos.processors.CartProcessor;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by taozhang on 1/5/15.
 */
public class ConsolePrinter {
    public static final String CONCLUSION_TITLE = "总计金额\t\t\t优惠前\t优惠后\t\t优惠差价";
    public static final String HEADER_TITLE = "购物明细\t\t\t数量\t\t单价\t\t\t小计";
    private CartProcessor cartProcessor;
    private PrintStream out;

    public ConsolePrinter(CartProcessor cartProcessor, PrintStream out) {
        this.cartProcessor = cartProcessor;
        this.out = out;
    }

    public void outToConsole() throws IOException {
        double totalPay = cartProcessor.getTotal();
        double originTotalPay = cartProcessor.getOriginTotal();
        out.println(HEADER_TITLE);
        for (Item item : cartProcessor.getCart().getCartList()) {
            out.println(item.getGood().getBarcode()+"\t\t"+item.getCount()+"\t\t"+item.getGood().getPrice()+"\t\t"+cartProcessor.getSubTotal(item));
        }
        out.println(CONCLUSION_TITLE);
        out.println(totalPay+"\t\t\t"+originTotalPay+"\t"+totalPay+"\t\t" +(originTotalPay - totalPay));
    }
}
