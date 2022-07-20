package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) throws IOException {
        Path filePath = createPathFromFileName(fileName);
        try (Stream<String> fileLinesStream = openFileLinesStream(filePath)){
            return fileLinesStream.collect(Collectors.joining("\n"));
        }
    }

    private static Stream<String> openFileLinesStream(Path filePath) {
        try {
            return Files.lines(filePath);
        } catch (IOException e){
            throw new RuntimeException("Cannot create stream of file lines", e);
        }
    }

    private static Path createPathFromFileName(String fileName) {
        Objects.requireNonNull(fileName);
        URL url = FileReaders.class.getClassLoader().getResource(fileName);
        try {
            return Paths.get(url.toURI());
        } catch (URISyntaxException e){
            throw new RuntimeException("Invalid file URL", e);
        }
    }
}
