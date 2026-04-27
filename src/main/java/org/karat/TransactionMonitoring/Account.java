package org.karat.TransactionMonitoring;

import java.util.Objects;

public class Account {
    /** Data about a bank account. */
    String accountNumber;
    String accountHolder;
    String accountType;     // "Checking", "Savings", "Credit"

    Account(String accountNumber, String accountHolder, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Account account = (Account) other;
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
