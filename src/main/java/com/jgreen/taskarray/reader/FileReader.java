package com.jgreen.taskarray.reader;

import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<String> readLines(String filePath) throws IOException;
}
