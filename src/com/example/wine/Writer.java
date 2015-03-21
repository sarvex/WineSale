package com.example.wine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Writes the ResultSet in TSV format
 *
 * Created by Sarvex on 18/03/2015.
 */
public class Writer {
    private static final String TAB = "\t";
    private static final String NEW_LINE = "\n";
    private static StringBuilder output;

    /**
     * Write the TSV file
     *
     * @param file
     */
    public static void write(String file) {
        Writer.output = new StringBuilder();

        output.append("Sold ").append(Processor.getSold()).append(" wines out of ").append(Processor.getTotal()).append(NEW_LINE);
        Processor.getResult().entrySet().stream().forEach(entry -> process(entry.getKey(), entry.getValue()));

        try {
            Files.write(Paths.get(file), output.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process all sales for a person
     *
     * @param person
     * @param wines
     */
    private static void process(String person, List<String> wines) {
        wines.stream().forEach(wine -> output.append(person).append(TAB).append(wine).append(NEW_LINE));
    }
}
