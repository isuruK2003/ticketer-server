package me.ticketing_system.simulation;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class SimulationConfigurationRepository {
    private final JdbcClient jdbcClient;

    public SimulationConfigurationRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<SimulationConfiguration> findAll() {
        String sqlQuarry = "SELECT * FROM simulation_configurations";
        return jdbcClient.sql(sqlQuarry)
                .query(SimulationConfiguration.class)
                .list();
    }

    public void createConfig(SimulationConfiguration newConfig) {
        String sqlQuery = """
            INSERT INTO simulation_configurations
                (totalTicketsForVendor, totalTicketsForCustomer, totalVendors, totalConsumers, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity)
            VALUES
                (?, ?, ?, ?, ?, ?, ?)
            """;
        int created = jdbcClient.sql(sqlQuery)
                .params(List.of(
                        newConfig.totalTicketsForVendor(),
                        newConfig.totalTicketsForCustomer(),
                        newConfig.totalVendors(),
                        newConfig.totalConsumers(),
                        newConfig.ticketReleaseRate(),
                        newConfig.customerRetrievalRate(),
                        newConfig.maxTicketCapacity()
                ))
                .update();
        Assert.state(created == 1, "Failed to create the configuration: " + newConfig);
    }

    public void deleteConfig(Integer id) {
        String sqlQuarry = "DELETE FROM simulation_configurations WHERE id=:id";
        int deleted = jdbcClient.sql(sqlQuarry)
                .param(id, id)
                .update();
        Assert.state(deleted == 1, "Failed to delete the configuration with configId: " + id);
    }
}
