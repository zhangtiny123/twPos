package com.tw.pos;

import com.tw.pos.inDoor.FileContentReader;
import com.tw.pos.helper.FileSource;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by taozhang on 12/24/14.
 */
public class FileContentReaderTest {

    //when

    @Test
    public void should_return_the_cart_info_when_given_the_cart_file_path() throws IOException {
        //given
        String filePath = FileSource.CART_INFO_FILE_PATH;
        FileContentReader fReader = new FileContentReader();

        //when
        List<String> readResultList = fReader.readData(filePath);

        //then
        assertThat(readResultList.size(), is(7));
        assertThat(readResultList.get(0), is("ITEM000001"));

    }

    @Test
    public void should_return_file_not_find_when_given_illegal_file_path() throws IOException {
        //given
        String filePath = "./src/main/resources/datas/ffff.txt";
        FileContentReader fReader = new FileContentReader();

        //when
        List<String> readResultList = fReader.readData(filePath);

        //then
        assertThat(readResultList.size(), is(1));
        assertThat(readResultList.get(0), is("illegal input file path!"));

    }

    @Test
    public void should_return_null_when_given_a_null_string() throws IOException {
        //given
        String filePath = "";
        FileContentReader fReader = new FileContentReader();

        //when
        List<String> readResultList = fReader.readData(filePath);

        assertThat(readResultList.size(), is(1));
        assertThat(readResultList.get(0), is("illegal input file path!"));

    }
}
