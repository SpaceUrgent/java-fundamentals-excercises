package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */

    private final Map<Character, Long> characterCountMap;
    private final char mostPopularCharacter;


    private FileStats(String fileName){
        Path filePath = getFilePath(fileName);
        characterCountMap = computeCharacterMap(filePath);
        mostPopularCharacter = findMostPopularCharacter(characterCountMap);
    }

    private char findMostPopularCharacter(Map<Character, Long> characterCountMap) {
        return characterCountMap.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }

    private Map<Character, Long> computeCharacterMap(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)){
            return collectCharactersToCountMap(lines);
        } catch (IOException e){
            throw new FileStatsException("Cannot read the file", e);
        }
    }

    private Map<Character, Long> collectCharactersToCountMap(Stream<String> lines) {
        return lines
                .flatMapToInt(String::chars)
                .filter(a -> a != 32)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private Path getFilePath(String fileName) {
        Objects.requireNonNull(fileName);
        URL fileUrl = getFileUrl(fileName);
        try{
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e){
            throw new RuntimeException("Wrong file path", e);
        }
    }

    private URL getFileUrl(String fileName) {
        URL fileUrl = getClass().getClassLoader().getResource(fileName);
        if (fileUrl == null){
            throw new FileStatsException("Wrong file path");
        }
        return fileUrl;
    }

    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return characterCountMap.get(character).intValue();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return mostPopularCharacter;
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {

        return characterCountMap.containsKey(character);

    }
}
