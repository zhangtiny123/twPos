package com.tw.pos;

import java.io.IOException;
import java.util.LinkedHashMap;
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
