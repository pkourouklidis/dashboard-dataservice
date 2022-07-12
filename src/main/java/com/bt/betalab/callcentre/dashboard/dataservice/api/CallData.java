/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CALLS")
public class CallData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long callId;

    private boolean isEasy;
    private Customer customer;

    private long callDelay;
    private Timestamp arrivalTime;
    private Timestamp pickupTime;
    private Timestamp closingTime;
    private boolean isBounced = false;
    private boolean isSolved = false;
    private WorkerDetails workerDetails;

    private int workers;

    private String simulationId;
    private Timestamp simulationStartTime;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Timestamp getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Timestamp closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isEasy() {
        return isEasy;
    }

    public void setEasy(boolean easy) {
        isEasy = easy;
    }

    public boolean isBounced() {
        return isBounced;
    }

    public void setBounced(boolean bounced) {
        isBounced = bounced;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public String getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(String simulationId) {
        this.simulationId = simulationId;
    }

    public Timestamp getSimulationStartTime() {
        return simulationStartTime;
    }

    public void setSimulationStartTime(Timestamp simulationStartTime) {
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
