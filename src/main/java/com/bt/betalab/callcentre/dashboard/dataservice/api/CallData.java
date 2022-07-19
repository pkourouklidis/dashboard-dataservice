/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "calls")
public class CallData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private boolean isEasy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    private long callDelay;
    private Instant arrivalTime;
    private Instant pickupTime;
    private Instant closingTime;
    private boolean isBounced = false;
    private boolean isSolved = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workerDetails_id", referencedColumnName = "id")
    private WorkerDetails workerDetails;

    private int workers;

    private String simulationId;
    private Instant simulationStartTime;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Instant arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Instant getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Instant pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Instant getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Instant closingTime) {
        this.closingTime = closingTime;
    }

    public boolean getIsEasy() {
        return isEasy;
    }

    public void setIsEasy(boolean easy) {
        isEasy = easy;
    }

    public boolean getIsBounced() {
        return isBounced;
    }

    public void setIsBounced(boolean bounced) {
        isBounced = bounced;
    }

    public boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean solved) {
        isSolved = solved;
    }

    public String getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(String simulationId) {
        this.simulationId = simulationId;
    }

    public Instant getSimulationStartTime() {
        return simulationStartTime;
    }

    public void setSimulationStartTime(Instant simulationStartTime) {
        this.simulationStartTime = simulationStartTime;
    }

    public WorkerDetails getWorkerDetails() {
        return workerDetails;
    }

    public void setWorkerDetails(WorkerDetails workerDetails) {
        this.workerDetails = workerDetails;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public long getCallDelay() {
        return callDelay;
    }

    public void setCallDelay(long callDelay) {
        this.callDelay = callDelay;
    }
}
