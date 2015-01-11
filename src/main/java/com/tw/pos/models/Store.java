package com.tw.pos.models;

import com.tw.pos.processors.GoodListPreProcessor;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by taozhang on 1/9/15.
 */
public class Store {
    private List<Good> goodList;

    public Store(GoodListPreProcessor goodListPreProcessor) throws IOException {
        this.goodList = goodListPreProcessor.process();
    }

    public List<Good> getGoodList() {
        return goodList;
    }
}
