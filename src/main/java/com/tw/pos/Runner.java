package com.tw.pos;

import java.io.IOException;

/**
 * Created by taozhang on 12/30/14.
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        FileContentReader fileContentReader = new FileContentReader();
        ItemListPreProcessor itemList = new ItemListPreProcessor(fileContentReader);
        CartPreProcessor cart = new CartPreProcessor(fileContentReader, itemList);
        DiscountPromotionHandler discount = new DiscountPromotionHandler(fileContentReader);
        SecondHalfHandler secondHalf = new SecondHalfHandler(fileContentReader);

        double totalPayments = 0;
        double totalPaymentsBeforeDiscount = 0;
        System.out.println("购物明细     数量    单价   小计");
        for (Item item : cart.process_list()) {
            double itemTotal = 0;
            if (discount.is_discounted(item.getGood())){
                itemTotal = discount.discountFor(item);
            }
            if (secondHalf.isSecondHalf(item.getGood())) {
                itemTotal = secondHalf.discountFor(item);
            }
            if (!(discount.is_discounted(item.getGood()) || secondHalf.isSecondHalf(item.getGood()))){
                itemTotal = item.calculatePayments();
            }
            totalPayments += itemTotal;
            totalPaymentsBeforeDiscount += item.getGood().getPrice() * item.getCount();
            System.out.println(item.getGood().getBarcode()+"   "+item.getCount()+"    "+item.getGood().getPrice()+"    "
                    +itemTotal);
        }
        System.out.println("总计金额     优惠前   优惠后   优惠差价");
        System.out.println(totalPayments+"       "+totalPaymentsBeforeDiscount+"   "+totalPayments+"   "
                +(totalPaymentsBeforeDiscount - totalPayments));
    }
}
