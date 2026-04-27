package org.karat.TransactionMonitoring;

public class Transaction {
    /** Data about a bank transaction. */
    Account account;
    String type;            // "Deposit", "Withdrawal", "Transfer"
    double amount;
    String date;            // Format: "YYYY-MM-DD"
    String time;            // Format: "HH:MM"

    Transaction(Account account, String type, double amount, String date, String time) {
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public Account getAccount() {
        return account;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
