package com.tw.pos.processors;

import com.tw.pos.door.FileContentReader;
import com.tw.pos.helper.FileSource;
import com.tw.pos.models.Good;
import com.tw.pos.models.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozhang on 12/27/14.
 */
public class CartPreProcessor {
    public FileContentReader fileContentReader;
    public ItemListPreProcessor itemListPreProcessor;

    public CartPreProcessor(FileContentReader fileContentReader, ItemListPreProcessor itemListPreProcessor) {
        this.fileContentReader = fileContentReader;
        this.itemListPreProcessor = itemListPreProcessor;
    }

    public List<Item> process_list() throws IOException {
        List<Item> itemList = new ArrayList<Item>();
        List<String> stringList = fileContentReader.readData(FileSource.CART_INFO_FILE_PATH);
        int index;
        for (String temp : stringList) {
            index = containItem(itemList, temp);
            if(index != -1) {
                itemList.get(index).plusCount(getItemCount(temp));
            }
            else {
                String barcode = getBarcode(temp);
                double singlePrice = getPriceByBarcode(barcode);
                itemList.add(new Item(new Good(barcode, singlePrice), getItemCount(temp)));
            }
        }

        return itemList;
    }

    private double getPriceByBarcode(String barcode) throws IOException {
        List<Good> goodList = itemListPreProcessor.process();
        for (Good good : goodList) {
            if (good.getBarcode().equals(barcode)) {
                return good.getPrice();
            }
        }
        return 0;
    }

    private int containItem(List<Item> itemList, String item) {
        int i = 0;
        for (Item item1 : itemList) {
            if (item1.getGood().getBarcode().equals(getBarcode(item))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private String getBarcode(String listItem) {
        return listItem.split("-")[0];
    }

    private int getItemCount(String listItem) {
        if (listItem.length() == 10) {
            return 1;
        }
        return Integer.parseInt(listItem.split("-")[1]);
    }
}
