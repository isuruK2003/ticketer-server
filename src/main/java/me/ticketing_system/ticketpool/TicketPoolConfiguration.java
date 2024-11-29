package me.ticketing_system.ticketpool;

public class TicketPoolConfiguration {
    private Integer configId;
    private Integer totalTickets;
    private Integer ticketReleaseRate;
    private Integer customerRetrievalRate;
    private Integer maximumTicketCapacity;
    
    public TicketPoolConfiguration( Integer configId, Integer totalTickets, Integer ticketReleaseRate,
                                    Integer customerRetrievalRate, Integer maximumTicketCapacity ) {
        this.configId = configId;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTicketCapacity = maximumTicketCapacity;
    }

    public Integer getConfigId() {
        return configId;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public Integer getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public Integer getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public Integer getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }
}
