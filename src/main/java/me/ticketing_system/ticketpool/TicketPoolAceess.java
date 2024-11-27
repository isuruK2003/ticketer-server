package me.ticketing_system.ticketpool;

import me.ticketing_system.customer.Customer;
import me.ticketing_system.vendor.Vendor;

public class TicketPoolAceess {
    Ticket ticket;
    Vendor vendor;
    Customer customer;

    public TicketPoolAceess(Ticket newTicket, Vendor vendor, Customer customer) {
        this.ticket = newTicket;
        this.vendor = vendor;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public Vendor getVendor() {
        return vendor;
    }
    
    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "TicketPoolAceess [ticket=" + ticket + ", vendor=" + vendor + ", customer=" + customer + "]";
    }
}
