package org.karat.DeliveryTracking;

import org.junit.Assert;

public class DeliverySolution {
    public static void main(String[] args) {
        testDeliveryRecord();
        testDeliveryTracker();
        testGetOnTimeDeliveryRate();
    }

    public static void testDeliveryRecord() {
        System.out.println("Running testDeliveryRecord");
        Package pkg = new Package("TRK001", "123 Main St", "456 Oak Ave", 2.5, "Standard");
        DeliveryRecord record = new DeliveryRecord(pkg, "John Driver", "2024-02-01",
                "2024-02-05", "2024-02-04", "Delivered");

        Assert.assertTrue(record.isOnTime());
        Assert.assertEquals("Delivered", record.status);
    }

    public static void testDeliveryTracker() {
        System.out.println("Running testDeliveryTracker");
        DeliveryTracker tracker = new DeliveryTracker("Fast Delivery Co.");

        Assert.assertEquals(0, tracker.getTotalDeliveries());

        Package p1 = new Package("TRK001", "Addr1", "Addr2", 2.5, "Standard");
        Package p2 = new Package("TRK002", "Addr3", "Addr4", 1.0, "Express");
        Package p3 = new Package("TRK003", "Addr5", "Addr6", 3.5, "Overnight");

        DeliveryRecord d1 = new DeliveryRecord(p1, "Driver A", "2024-02-01", "2024-02-05", "2024-02-04", "Delivered");
        DeliveryRecord d2 = new DeliveryRecord(p2, "Driver B", "2024-02-02", "2024-02-03", "2024-02-03", "Delivered");
        DeliveryRecord d3 = new DeliveryRecord(p3, "Driver A", "2024-02-03", "2024-02-05", null, "In Transit");

        tracker.addDelivery(d1);
        tracker.addDelivery(d2);
        tracker.addDelivery(d3);

        Assert.assertEquals(3, tracker.getTotalDeliveries());
        Assert.assertEquals(2, tracker.getDeliveriesByStatus("Delivered"));
        Assert.assertEquals(2, tracker.getDeliveriesByDriver("Driver A"));
        Assert.assertEquals(2.33, tracker.getAveragePackageWeight(), 0.01);
    }

    public static void testGetOnTimeDeliveryRate() {
        System.out.println("Running testGetOnTimeDeliveryRate");
        DeliveryTracker tracker = new DeliveryTracker("Fast Delivery Co.");

        Package p1 = new Package("TRK001", "Addr1", "Addr2", 2.5, "Standard");
        Package p2 = new Package("TRK002", "Addr3", "Addr4", 1.0, "Express");
        Package p3 = new Package("TRK003", "Addr5", "Addr6", 3.5, "Overnight");
        Package p4 = new Package("TRK004", "Addr7", "Addr8", 2.0, "Standard");
        Package p5 = new Package("TRK005", "Addr9", "Addr10", 1.5, "Express");

        // 8 on-time deliveries out of 10 completed
        DeliveryRecord d1 = new DeliveryRecord(p1, "Driver A", "2024-02-01", "2024-02-05", "2024-02-04", "Delivered");
        DeliveryRecord d2 = new DeliveryRecord(p2, "Driver B", "2024-02-01", "2024-02-03", "2024-02-03", "Delivered");
        DeliveryRecord d3 = new DeliveryRecord(p3, "Driver A", "2024-02-01", "2024-02-05", "2024-02-06", "Delivered");
        DeliveryRecord d4 = new DeliveryRecord(p4, "Driver B", "2024-02-01", "2024-02-04", "2024-02-03", "Delivered");
        DeliveryRecord d5 = new DeliveryRecord(p5, "Driver A", "2024-02-01", "2024-02-05", null, "In Transit");

        tracker.addDelivery(d1);
        tracker.addDelivery(d2);
        tracker.addDelivery(d3);
        tracker.addDelivery(d4);
        tracker.addDelivery(d5);

        Assert.assertEquals(75.0, tracker.getOnTimeDeliveryRate(), 0.01);
        //System.out.println(tracker());// 3 out of 4 completed = 75%
    }
}
