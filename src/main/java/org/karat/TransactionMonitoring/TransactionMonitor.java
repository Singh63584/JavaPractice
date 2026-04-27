package org.karat.TransactionMonitoring;

import java.util.ArrayList;
import java.util.Comparator;

public class TransactionMonitor {

    /**
     * Manages transactions and provides fraud detection capabilities.
     */
    ArrayList<Transaction> transactions = new ArrayList<>();

    TransactionMonitor() {
    }

    void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    int getTotalTransactions() {
        return transactions.size();
    }

    double getTotalDeposits() {
        return transactions.stream().filter(t -> t.type.equals("Deposit")).mapToDouble(t -> t.amount).sum();
    }

    double getTotalWithdrawals() {
        return transactions.stream().filter(t -> t.type.equals("Withdrawal")).mapToDouble(t -> t.amount).sum();
    }

    double getAverageTransactionAmount() {
        /**
         * Returns the average transaction amount.
         * BUG: This method has a bug - fix it!
         */

        if (transactions.isEmpty()) {
            return 0.0;
        }

        double total = transactions.stream().mapToDouble(t -> t.amount).sum();
        return total / getTotalTransactions();
    }

    int getTransactionCountForAccount(String accountNumber) {
        /** Returns the count of transactions for a specific account. */
        return (int) transactions.stream().filter(t -> t.account.accountNumber.equals(accountNumber)).count();
    }

    public boolean detectLargeWithdrawalPattern(String accountNumber) {
        Double largest = transactions.stream().filter(e -> e.getType().equals("Withdrawal")).map(e -> e.getAmount()).sorted(Comparator.reverseOrder()).findFirst().get();
        System.out.println(largest);
        for (Transaction t : transactions) {
            if (t.getType().equals("Withdrawal") && t.getAccount().getAccountNumber().equals(accountNumber)) {
                if (largest <= t.getAmount()) {
                    System.out.println(t.toString());
                    return true;
                }
            }
        }
        return false;
    }
}
