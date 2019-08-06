package com.java.task.datareader;



import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private final static Logger LOG = Logger.getLogger(DataReader.class);

    public List<String> readData(String fileName) {

        List<String> dataList = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileName), Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine()) != null) {
                dataList.add(line);
            }
        } catch (IOException e) {
            LOG.error(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.info("Reader do not closed");
                }
            }
        }
        return dataList;
    }
}