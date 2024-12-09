package me.ticketing_system.ticketpool;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class Ticket {
    @PositiveOrZero
    private Integer ticketId;
    @NotNull
    private Integer vendorId;
    private Integer consumerId;

    public Ticket(Integer ticketId, Integer vendorId) {
        this.ticketId = ticketId;
        this.vendorId = vendorId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", vendorId=" + vendorId +
                ", consumerId=" + consumerId +
                '}';
    }
}