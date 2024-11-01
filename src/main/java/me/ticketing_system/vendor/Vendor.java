package me.ticketing_system.vendor;

public class Vendor {
    private String vendorId;
    private String vendorName;

    Vendor (String vendorId, String vendorName) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId='" + vendorId + '\'' +
                ", vendorName='" + vendorName + '\'' +
                '}';
    }
}
