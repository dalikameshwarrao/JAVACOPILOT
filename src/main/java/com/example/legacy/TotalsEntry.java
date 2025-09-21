package com.example.legacy;

public class TotalsEntry {
    private final String user;
    private final double total;
    private final double average;

    public TotalsEntry(String user, double total, double average) {
        this.user = user;
        this.total = total;
        this.average = average;
    }

    public String getUser() { return user; }
    public double getTotal() { return total; }
    public double getAverage() { return average; }

    @Override
    public String toString() {
        return "{" + "user='" + user + '\'' + ", total=" + total + ", average=" + average + '}';
    }
}
