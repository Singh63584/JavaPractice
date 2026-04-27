package org.karat.StockSulution;

import java.time.LocalDate;
import java.util.Objects;

public class PriceRecord {
    private final LocalDate date;
    private final double price;

    public PriceRecord(LocalDate date, double price) {
        this.date = Objects.requireNonNull(date, "date");
        this.price = price;
    }

    /**
     * Convenience: accept ISO-8601 date (yyyy-MM-dd).
     */
    public PriceRecord(String isoDate, double price) {
        this(LocalDate.parse(isoDate), price);
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return date + " -> " + price;
    }
}
