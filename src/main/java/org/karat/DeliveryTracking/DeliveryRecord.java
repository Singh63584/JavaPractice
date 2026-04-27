package org.karat.DeliveryTracking;

public class DeliveryRecord {
    /** Data about a delivery. */
    Package packageObj;
    String driverName;
    String shipDate;                // Format: "YYYY-MM-DD"
    String expectedDeliveryDate;    // Format: "YYYY-MM-DD"
    String actualDeliveryDate;      // Format: "YYYY-MM-DD", null if not delivered yet
    String status;                  // "In Transit", "Delivered", "Failed"

    DeliveryRecord(Package packageObj, String driverName, String shipDate,
                   String expectedDeliveryDate, String actualDeliveryDate, String status) {
        this.packageObj = packageObj;
        this.driverName = driverName;
        this.shipDate = shipDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
        this.status = status;
    }

    boolean isOnTime() {
        /** Check if delivery was on time. Returns false if not yet delivered. */
        if (actualDeliveryDate == null) return false;
        // Simple date comparison (YYYY-MM-DD format allows string comparison)
        return actualDeliveryDate.compareTo(expectedDeliveryDate) <= 0;
    }
}
