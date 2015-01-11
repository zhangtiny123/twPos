package com.tw.pos.discounts;

import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.helper.FileSource;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;

import java.io.IOException;
import java.util.List;

/**
 * Created by taozhang on 12/30/14.
 */
public class SecondHalfPromotion {
    private List<String> secondHalfList;

    public SecondHalfPromotion(FileContentReader fileContentReader) throws IOException {
        this.secondHalfList = fileContentReader.readData(FileSource.SECOND_HALF_PROMOTION_INFO_FILE_PATH);
    }

    public Boolean isSecondHalf(Good good) throws IOException {
        return secondHalfList.contains(good.getBarcode());
    }

    public double discountFor(Item item) throws IOException {
        if (isSecondHalf(item.getGood())) {
            return item.calculatePayments() - (item.getGood().getDiscountedPrice()/2) * (item.getCount()/2);
        }
        return 0;
    }
}
