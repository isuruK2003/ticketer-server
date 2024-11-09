package me.ticketing_system.vendor;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class VendorRepository {
    private final JdbcClient jdbcClient;

    public VendorRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Vendor> findAll() {
        return jdbcClient.sql("SELECT * FROM vendors")
                .query(Vendor.class)
                .list();
    }

    public Optional<Vendor> findById(String vendorId) {
        return jdbcClient.sql("SELECT * FROM vendors WHERE vendorId = :vendorId ")
                .param("vendorId", vendorId)
                .query(Vendor.class)
                .optional();
    }

    public void createVendor(Vendor newVendor) {
        int created = jdbcClient.sql("INSERT INTO vendors (vendorId, vendorName) VALUES (?, ?)")
                .params(List.of(newVendor.getVendorId(), newVendor.getVendorName()))
                .update();

        Assert.state(created == 1, "Failed to create vendor: " + newVendor);
    }

    public void updateVendor(Vendor updatedVendor) {
        Assert.notNull(updatedVendor, "Vendor object cannot be null");
        int updated = jdbcClient.sql("UPDATE vendors SET vendorName = ? WHERE vendorId = :vendorId")
                .param("vendorId", updatedVendor.getVendorId())
                .params(List.of(updatedVendor.getVendorName()))
                .update();

        Assert.state(updated == 1, "Failed to update vendor: " + updatedVendor);
    }

    public void deleteVendor(String vendorId) {
        String sqlQuarry = "DELETE FROM vendors WHERE vendorId=:vendorId";
        int deleted = jdbcClient.sql(sqlQuarry)
                .param("vendorId", vendorId)
                .update();
        Assert.state(deleted == 1, "Failed to delete the vendor with vendorId: " + vendorId);
    }
}
