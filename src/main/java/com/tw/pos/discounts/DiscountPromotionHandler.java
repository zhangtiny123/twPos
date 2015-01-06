package com.tw.pos.discounts;

import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.helper.FileSource;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taozhang on 12/30/14.
 */
public class DiscountPromotionHandler {
    private FileContentReader fileContentReader;

    public DiscountPromotionHandler(FileContentReader fileContentReader) {
        this.fileContentReader = fileContentReader;
    }

    private Map<String, Integer> getDiscountPromotionMap() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<String> stringList = fileContentReader.readData(FileSource.DISCOUNT_PROMOTION_INFO_FILE_PATH);
        for (String temp : stringList) {
            String[] strings = temp.split(":");
            map.put(strings[0], Integer.parseInt(strings[1]));
        }
        return map;
    }

    public Boolean is_discounted(Good good) throws IOException {
        Map<String, Integer> map = getDiscountPromotionMap();
        return map.containsKey(good.getBarcode());
    }

    public double discountFor(Item item) throws IOException {
        if (is_discounted(item.getGood())) {
            Map<String, Integer> map = getDiscountPromotionMap();
            item.getGood().setDiscountedPrice(item.getGood().getPrice()*map.get(item.getGood().getBarcode())*0.01);
            return item.calculatePayments();
        }
        return 0;
    }
}
