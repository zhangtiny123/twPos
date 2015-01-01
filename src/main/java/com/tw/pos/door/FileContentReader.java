package com.tw.pos.door;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taozhang on 12/24/14.
 */
public class FileContentReader {
    public List<String> readData(String toReadFilePath) throws IOException {
        File file = new File(toReadFilePath);
        String temp = "";
        List<String> resultList = new ArrayList<String>();

        if(file.isFile() && file.exists()) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while ((temp = bufferedReader.readLine()) != null) {
                resultList.add(temp);
            }
        }
        else {
            resultList.add("illegal input file path!");
        }
        return resultList;
    }
}
