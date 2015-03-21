package com.example.wine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Reads the TSV file and builds the required dataset
 * Created by Sarvex on 18/03/2015.
 */
public class Reader {

    private static final String TAB = "\t";

    /**
     * Read the whole file
     *
     * @param file
     */
    public static void read(String file){
        final Path path = Paths.get(file);

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> process(line));
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Process a single line in TSV to add bids and wishes.
     *
     * @param line
     */
    public static void process(String line) {
        final List<String> tokens = Arrays.asList(line.split(TAB));
        Processor.addBid(tokens.get(1), tokens.get(0));
        Processor.addWish(tokens.get(0), tokens.get(1));
    }
}
