package me.ticketing_system.ticketpool;

public class TicketPoolConfiguration {
    private final Integer configId;
    private final Integer totalTickets;
    private final Integer ticketReleaseRate;
    private final Integer customerRetrievalRate;
    private final Integer maximumTicketCapacity;

    public TicketPoolConfiguration( Integer configId, Integer totalTickets, Integer ticketReleaseRate,
                                    Integer customerRetrievalRate, Integer maximumTicketCapacity ) {
        this.configId = configId;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTicketCapacity = maximumTicketCapacity;
    }

    public Integer getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }
}
