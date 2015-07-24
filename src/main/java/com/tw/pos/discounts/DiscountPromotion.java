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
public class DiscountPromotion {
    private List<String> discountList;

    public DiscountPromotion(FileContentReader fileContentReader) throws IOException {
        this.discountList = fileContentReader.readData(FileSource.DISCOUNT_PROMOTION_INFO_FILE_PATH);
    }

    private Map<String, Integer> getDiscountPromotionMap() throws IOException {
        Map<String, Integer> promotionMap = new HashMap<String, Integer>();
        for (String temp : discountList) {
            String[] strings = temp.split(":");
            promotionMap.put(strings[0], Integer.parseInt(strings[1]));
        }
        return promotionMap;
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
