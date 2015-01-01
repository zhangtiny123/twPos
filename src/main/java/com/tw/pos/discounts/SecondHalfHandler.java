package com.tw.pos.discounts;

import com.tw.pos.door.FileContentReader;
import com.tw.pos.helper.FileSource;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;

import java.io.IOException;
import java.util.List;

/**
 * Created by taozhang on 12/30/14.
 */
public class SecondHalfHandler {
    FileContentReader fileContentReader;

    public SecondHalfHandler(FileContentReader fileContentReader) {
        this.fileContentReader = fileContentReader;
    }

    public Boolean isSecondHalf(Good good) throws IOException {
        List<String> stringList = fileContentReader.readData(FileSource.SECOND_HALF_PROMOTION_INFO_FILE_PATH);
        return stringList.contains(good.getBarcode());
    }

    public double discountFor(Item item) throws IOException {
        if (isSecondHalf(item.getGood())) {
            return item.calculatePayments() - (item.getGood().getDiscountedPrice()/2) * (item.getCount()/2);
        }
        return 0;
    }
}
