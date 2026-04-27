package org.karat.StockSulution;

import java.time.LocalDate;
import java.util.*;

public class StockCollection {
    private final Stock stock;
    private final List<PriceRecord> records = new ArrayList<>();

    public StockCollection(Stock stock) {
        this.stock = Objects.requireNonNull(stock, "stock");
    }

    public void addRecord(PriceRecord record) {
        records.add(Objects.requireNonNull(record, "record"));
    }

    public int size() {
        return records.size();
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public OptionalDouble getMinPrice() {
        return records.stream().mapToDouble(PriceRecord::getPrice).min();
    }

    public OptionalDouble getMaxPrice() {
        return records.stream().mapToDouble(PriceRecord::getPrice).max();
    }

    public OptionalDouble getAveragePrice() {
        return records.stream().mapToDouble(PriceRecord::getPrice).average();
    }

    /**
     * Returns the largest absolute change between any two consecutive records (by date),
     * along with the pair of dates as a List:
     * [maxChange, "startDate", "endDate"]
     * <p>
     * - Two days are "consecutive" if no other record date lies between them
     * in chronological order within this collection.
     * - If there are fewer than 2 records, returns an empty list.
     * - The change is the absolute difference in price; the date pair corresponds to the two records.
     * <p>
     * Example: [20.0, "2023-06-25", "2023-06-29"]
     */

    public List<Object> getBiggestChange() {
        List<PriceRecord> sorted = new ArrayList<>(records);
        sorted.sort(Comparator.comparing(PriceRecord::getDate));
        if (sorted.size() < 2) return Collections.emptyList();

        double maxChange = -1.0;
        LocalDate bestStart = null, bestEnd = null;

        for (int i = 1; i < sorted.size(); i++) {
            PriceRecord prev = sorted.get(i - 1);
            PriceRecord curr = sorted.get(i);
            double change = Math.abs(curr.getPrice() - prev.getPrice());
            if (change > maxChange) {
                maxChange = change;
                bestStart = prev.getDate();
                bestEnd = curr.getDate();
            }
        }
        return Arrays.asList(maxChange, bestStart.toString(), bestEnd.toString());
    }
    public static void main(String[] args) {
        Stock stock = new Stock("ACME", "Acme Corp");
        StockCollection sc = new StockCollection(stock);

        // Unsorted insertion (to confirm bug fix via proper chronological sorting)
        sc.addRecord(new PriceRecord("2023-06-29", 110));
        sc.addRecord(new PriceRecord("2023-07-01", 112));
        sc.addRecord(new PriceRecord("2023-06-25", 90));
        sc.addRecord(new PriceRecord("2023-07-06", 105));

        System.out.println("Sorted Records:");
        //sc.getRecordsSortedByDate().forEach(System.out::println);

        System.out.println("\nStats:");
        System.out.println("Count: " + sc.size());
        System.out.println("Min: " + sc.getMinPrice().orElse(Double.NaN));
        System.out.println("Max: " + sc.getMaxPrice().orElse(Double.NaN));
        System.out.println("Avg: " + sc.getAveragePrice().orElse(Double.NaN));

        System.out.println("\nBiggest Change [change, startDate, endDate]:");
        System.out.println(sc.getBiggestChange()); // Expected: [20.0, "2023-06-25", "2023-06-29"]
    }
}
