package org.karat.DeliveryTracking;

import java.util.Objects;

public class Package {
    /** Data about a package. */
    String trackingNumber;
    String senderAddress;
    String recipientAddress;
    double weight;          // in kilograms
    String packageType;     // "Standard", "Express", "Overnight"

    Package(String trackingNumber, String senderAddress, String recipientAddress,
            double weight, String packageType) {
        this.trackingNumber = trackingNumber;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.weight = weight;
        this.packageType = packageType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Package pkg = (Package) other;
        return trackingNumber.equals(pkg.trackingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingNumber);
    }
}
