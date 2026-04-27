package org.karat.TransactionMonitoring;

import org.junit.Assert;

public class TransactionSolution {
    public static void main(String[] args) {
        testTransaction();
        testTransactionMonitor();
        testDetectLargeWithdrawalPattern();
    }

    public static void testTransaction() {
        System.out.println("Running testTransaction");
        Account account = new Account("A001", "John Doe", "Checking");
        Transaction transaction = new Transaction(account, "Deposit", 1000.0, "2024-02-01", "10:30");

        Assert.assertEquals(account, transaction.account);
        Assert.assertEquals("Deposit", transaction.type);
        Assert.assertEquals(1000.0, transaction.amount, 0.01);
    }

    public static void testTransactionMonitor() {
        System.out.println("Running testTransactionMonitor");
        TransactionMonitor monitor = new TransactionMonitor();

        Assert.assertEquals(0, monitor.getTotalTransactions());
        Assert.assertEquals(0.0, monitor.getTotalDeposits(), 0.01);

        Account acc1 = new Account("A001", "Alice", "Checking");
        Account acc2 = new Account("A002", "Bob", "Savings");

        Transaction t1 = new Transaction(acc1, "Deposit", 1000.0, "2024-02-01", "10:00");
        Transaction t2 = new Transaction(acc1, "Withdrawal", 500.0, "2024-02-02", "11:00");
        Transaction t3 = new Transaction(acc2, "Deposit", 2000.0, "2024-02-03", "12:00");

        monitor.addTransaction(t1);
        monitor.addTransaction(t2);
        monitor.addTransaction(t3);

        Assert.assertEquals(3, monitor.getTotalTransactions());
        Assert.assertEquals(3000.0, monitor.getTotalDeposits(), 0.01);
        Assert.assertEquals(500.0, monitor.getTotalWithdrawals(), 0.01);
        Assert.assertEquals(1166.67, monitor.getAverageTransactionAmount(), 0.01);
        Assert.assertEquals(2, monitor.getTransactionCountForAccount("A001"));
    }

    public static void testDetectLargeWithdrawalPattern() {
        System.out.println("Running testDetectLargeWithdrawalPattern");
        TransactionMonitor monitor = new TransactionMonitor();

        Account acc1 = new Account("A001", "Alice", "Checking");
        Account acc2 = new Account("A002", "Bob", "Savings");

        // Account A001: 3 large withdrawals in same month
        Transaction t1 = new Transaction(acc1, "Withdrawal", 6000.0, "2024-02-05", "10:00");
        Transaction t2 = new Transaction(acc1, "Withdrawal", 5500.0, "2024-02-10", "11:00");
        Transaction t3 = new Transaction(acc1, "Withdrawal", 7000.0, "2024-02-15", "12:00");

        // Account A002: 2 large withdrawals in different months
        Transaction t4 = new Transaction(acc2, "Withdrawal", 6000.0, "2024-02-05", "13:00");
        Transaction t5 = new Transaction(acc2, "Withdrawal", 5500.0, "2024-03-10", "14:00");

        monitor.addTransaction(t1);
        monitor.addTransaction(t2);
        monitor.addTransaction(t3);
        monitor.addTransaction(t4);
        monitor.addTransaction(t5);

        Assert.assertTrue(monitor.detectLargeWithdrawalPattern("A001"));
        Assert.assertFalse(monitor.detectLargeWithdrawalPattern("A002"));
    }
}
