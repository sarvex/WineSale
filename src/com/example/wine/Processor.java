package com.example.wine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is the core computation logic for assigning sales
 *
 * Created by Sarvex on 18/03/2015.
 */
public class Processor {
    private static final int MAX_WINES = 3;

    // General housekeeping variables.
    private static int sold = 0;
    private static int maxSale = 0;
    private static int total = 0;

    // Dictionary containing list of all people wishing the particular wine
    private static Dictionary<String, List<String>> wines = new Dictionary<> (ArrayList.class);

    // Dictionary containing list of all the wines one person has wished for
    private static Dictionary<String, List<String>> people = new Dictionary<> (ArrayList.class);

    // Dictionary storing the sale results
    private static Dictionary<String, List<String>> result = new Dictionary<> (ArrayList.class);

    /**
     * Prepare the dictionaries for processing.
     */
    public static void initialize() {
        maxSale = people.size() * MAX_WINES;
        total = wines.size();

        // Find wines which are wished by only one person
        people.entrySet().stream()
                .forEach(entry -> processPeople(entry.getKey(), entry.getValue()));

        // Process rest of the wines.
        wines.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .forEach(entry -> processWines(entry.getKey(), entry.getValue()));
    }

    /** Sell wine to person when there is no other contender
     *
     * @param person
     * @param wishes
     */
    private static void processPeople(String person, List<String> wishes) {
        if (result.get(person).size() == MAX_WINES) return;
        wishes.stream()
                .filter(wish -> wines.get(wish).size() == 1)
                .forEach(wish -> {
                    result.get(person).add(wish);
                    wines.remove(wish);
                    sold++;
                });
    }

    /**Sell wine to first person in the list who does not have full quota
     *
     * @param wine
     * @param people
     */
    private static void processWines(String wine, List<String> people) {
        if (sold == maxSale) return;

        people.stream()
                .filter(person -> result.get(person).size() < MAX_WINES)
                .forEach(person -> {
                    result.get(person).add(wine);
                    sold++;
                });
        wines.get(wine).clear();
    }

    // Helper functions for data access
    public static void addBid (String wine, String person){
        wines.get(wine).add(person);
    }

    public static void addWish (String person, String wine){
        people.get(person).add(wine);
    }

    public static int getTotal() {
        return Processor.total;
    }

    public static int getSold() {
        return Processor.sold;
    }

    public static Map<String, List<String>> getResult() {
        return result;
    }
}
