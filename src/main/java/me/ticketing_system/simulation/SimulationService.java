package me.ticketing_system.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import me.ticketing_system.ticketpool.TicketPool;

@Service
public class SimulationService {
    private final List<Thread> vendorThreads;
    private final List<Thread> consumerThreads;
    private final TicketPool ticketPool;

    public SimulationService(TicketPool ticketPool) {
        this.vendorThreads = Collections.synchronizedList(new ArrayList<>());
        this.consumerThreads = Collections.synchronizedList(new ArrayList<>());
        this.ticketPool = ticketPool;
    }

    private void initializeList(List<Thread> threadList, Integer count, Vendor vendorObject) {
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(vendorObject);
            threadList.add(t);
        }
    }

    private void initializeList(List<Thread> threadList, Integer count, Consumer consumerObject) {
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(consumerObject);
            threadList.add(t);
        }
    }

    public void initializeAllLists(Integer vendorCount, Integer consumerCount) {
        // initialize the vendor threads list:
        initializeList(this.vendorThreads, vendorCount, new Vendor(this.ticketPool));
        // initialize the consumer list
        initializeList(this.vendorThreads, vendorCount, new Consumer(this.ticketPool));
    }

    private void executeThreads(List<Thread> threaList) {
        for (Thread thread : threaList) {
            thread.start();
        }
    }

    private void deexecuteThreads(List<Thread> threaList) {
        for (Thread thread : threaList) {
            thread.stop();
        }
    }

    public void startSimulation(Integer vendorCount, Integer consumerCount) {
        initializeAllLists(vendorCount, consumerCount);
        executeThreads(this.vendorThreads);
        executeThreads(this.consumerThreads);
    }

    public void endSimulation() {
        deexecuteThreads(this.vendorThreads);
        deexecuteThreads(this.consumerThreads);
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }
}
