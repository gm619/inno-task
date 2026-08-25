package com.jgreen.taskarray.reader.impl;

import com.jgreen.taskarray.reader.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> readLines(String relativeFilePath) throws IOException {
	    Path path = Paths.get(relativeFilePath);
	    return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
