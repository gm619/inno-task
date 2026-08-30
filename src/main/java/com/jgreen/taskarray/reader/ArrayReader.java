package com.jgreen.taskarray.reader;

import java.io.IOException;
import java.util.List;

import com.jgreen.taskarray.exception.CustomArrayExecption;

public interface ArrayReader {
    List<String> readLines(String filePath) throws IOException, CustomArrayExecption;
}
