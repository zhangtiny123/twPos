package com.tw.pos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozhang on 12/29/14.
 */
public class ItemListPreProcessor {
    private FileContentReader fileContentReader;

    public ItemListPreProcessor(FileContentReader fileContentReader) {
        this.fileContentReader = fileContentReader;
    }

    public List<Good> process() throws IOException {
        List<Good> goodList = new ArrayList<Good>();
        List<String> stringList = fileContentReader.readData(FileSource.ITEM_LIST_INFO_FILE_PATH);
        for (String stringListItem : stringList) {
            String[] stringResult = stringListItem.split(":");
            goodList.add(new Good(stringResult[0], Double.parseDouble(stringResult[1])));
        }
        return goodList;
    }
}
