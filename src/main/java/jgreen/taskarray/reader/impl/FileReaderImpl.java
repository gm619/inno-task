package jgreen.taskarray.reader.impl;

import jgreen.taskarray.reader.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderImpl implements FileReader {
    @Override
    public List<String> readLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path)
                .stream()
                .filter(line -> line != null)
                .collect(Collectors.toList());
    }
}
