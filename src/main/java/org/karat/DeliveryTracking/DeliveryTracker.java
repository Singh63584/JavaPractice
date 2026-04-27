package org.karat.DeliveryTracking;

import java.util.ArrayList;

class DeliveryTracker {
    /**
     * Manages delivery records and provides performance analytics.
     */
    ArrayList<DeliveryRecord> deliveries = new ArrayList<>();
    String companyName;

    DeliveryTracker(String companyName) {
        this.companyName = companyName;
    }

    void addDelivery(DeliveryRecord delivery) {
        deliveries.add(delivery);
    }

    int getTotalDeliveries() {
        return deliveries.size();
    }

    int getDeliveriesByStatus(String status) {
        /** Returns count of deliveries with a specific status. */
        return (int) deliveries.stream().filter(d -> d.status.equals(status)).count();
    }

    int getDeliveriesByDriver(String driverName) {
        /** Returns count of deliveries handled by a specific driver. */
        return (int) deliveries.stream().filter(d -> d.driverName.equals(driverName)).count();
    }

    double getAveragePackageWeight() {
        /**
         * Returns the average package weight.
         * BUG: This method has a bug - fix it!
         */
        //Bugs Fix
        if (deliveries.isEmpty()) {
            return 0.0;
        }

        double total = deliveries.stream().mapToDouble(d -> d.packageObj.weight).sum();
        return total / getTotalDeliveries();
    }

    public double getOnTimeDeliveryRate() {
        // Write your code here to solve this problem
        //Using Steam
//        long completedCount = deliveries.stream().filter(d -> d.actualDeliveryDate != null).count();
//        if (completedCount == 0) {
//            return 0.0;
//        }
//        long onTimeCount = deliveries.stream().filter(d -> d.actualDeliveryDate != null).filter(DeliveryRecord::isOnTime).count();

        int completedCount = 0;
        int onTimeCount = 0;

        for (DeliveryRecord d : deliveries) {
            if (d.actualDeliveryDate != null) {
                completedCount++;

                if (d.actualDeliveryDate.compareTo(d.expectedDeliveryDate) <= 0) {
                    onTimeCount++;
                }
            }
        }

        if (completedCount == 0) {
            return 0.0;
        }
        return (onTimeCount * 100.0) / completedCount;
    }
}
