package me.ticketing_system.customer;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private final JdbcClient jdbcClient;

    public CustomerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Customer> findAll() {
        return jdbcClient.sql("SELECT * FROM customers")
                .query(Customer.class)
                .list();
    }

    public Optional<Customer> findByID(String customerId) {
        return jdbcClient.sql("SELECT * FROM customers WHERE customerId = :customerId ")
                .param("customerId", customerId)
                .query(Customer.class)
                .optional();
    }

    public void createCustomer(Customer newCustomer) {
        int created = jdbcClient.sql("INSERT INTO customers (customerId, customerName) VALUES (?, ?)")
                .params(List.of(newCustomer.getCustomerId(), newCustomer.getCustomerName()))
                .update();
        Assert.state(created == 1, "Failed to create customer: " + newCustomer);
    }

    public void updateCustomer(Customer updatedCustomer) {
        Assert.notNull(updatedCustomer, "Customer object cannot be null");
        int updated = jdbcClient.sql("UPDATE customers SET customerName = ? WHERE customerId = :customerId")
                .param("customerId", updatedCustomer.getCustomerId())
                .params(List.of(updatedCustomer.getCustomerName()))
                .update();
        Assert.state(updated == 1, "Failed to update customer: " + updatedCustomer);
    }

    public void deleteCustomer(String customerId) {
        String sqlQuery = "DELETE FROM customers WHERE customerId=:customerId";
        int deleted = jdbcClient.sql(sqlQuery)
                .param("customerId", customerId)
                .update();
        Assert.state(deleted == 1, "Failed to delete the customer with customerId: " + customerId);
    }
}
