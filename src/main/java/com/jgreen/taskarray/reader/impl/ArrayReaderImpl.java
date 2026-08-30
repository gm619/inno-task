package com.jgreen.taskarray.reader.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.jgreen.taskarray.exception.CustomArrayExecption;

import com.jgreen.taskarray.reader.ArrayReader;

public class ArrayReaderImpl implements ArrayReader {
    @Override
    public List<String> readLines(String relativeFilePath) throws CustomArrayExecption {
        try {
            Path path = Paths.get(relativeFilePath);
	        return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CustomArrayExecption("Error reading file: " + relativeFilePath, e);
        }

    }
}
