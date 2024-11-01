package me.ticketing_system.config;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class ConfigRepository {
    private final JdbcClient jdbcClient;

    public ConfigRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Configuration> findAll() {
        String sqlQuarry = "SELECT * FROM configs";
        return jdbcClient.sql(sqlQuarry)
                .query(Configuration.class)
                .list();
    }

    public Optional<Configuration> findById(String configId) {
        String sqlQuarry = """
                SELECT
                    configId, totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity
                FROM
                    configs
                WHERE
                    configId = :configId
                """;
        return jdbcClient.sql(sqlQuarry)
                .param("configId", configId)
                .query(Configuration.class)
                .optional();
    }

    public void createConfig(Configuration newConfig) {
        String sqlQuarry = """
                INSERT INTO configs
                    (configId, totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity)
                VALUES
                    (?, ?, ?, ?, ?)
                """;
        int created = jdbcClient.sql(sqlQuarry)
                .params(List.of(
                        newConfig.configId(),
                        newConfig.totalTickets(),
                        newConfig.ticketReleaseRate(),
                        newConfig.customerRetrievalRate(),
                        newConfig.maximumTicketCapacity()))
                .update();

        Assert.state(created == 1, "Failed to create the configuration: " + newConfig);
    }

    public void updateConfig(Configuration newConfig) {
        String sqlQuarry = """
                UPDATE configs
                    SET totalTickets=?, ticketReleaseRate=?, customerRetrievalRate=?, maximumTicketCapacity=?
                WHERE
                    configId=?
                """;
        int updated = jdbcClient.sql(sqlQuarry)
                .params(List.of(
                        newConfig.totalTickets(),
                        newConfig.ticketReleaseRate(),
                        newConfig.customerRetrievalRate(),
                        newConfig.maximumTicketCapacity(),
                        newConfig.configId()))
                .update();

        Assert.state(updated == 1, "Failed to update the configuration: " + newConfig);
    }

    public void deleteConfig(String configId) {
        String sqlQuarry = "DELETE FROM configs WHERE configId=:configId";
        int deleted = jdbcClient.sql(sqlQuarry)
                .param("configId", configId)
                .update();
        Assert.state(deleted == 1, "Failed to delete the configuration with configId: " + configId);
    }
}
