/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class SimulationData {
    private String simulationId;
    private Instant startTime;
    private int workers = 0;
    private int totalCalls = 0;
    private int bouncedCalls = 0;
    private int resolvedCalls = 0;

    private int unresolvedCalls = 0;
    private long callDelay = 0;

    private long overallWaitTime = 0;
    private long averageWaitTime = 0;
    private long longestWaitTime = 0;
    private long shortestWaitTime = 0;

    private long overallServiceTime = 0;
    private long averageServiceTime = 0;
    private long longestServiceTime = 0;
    private long shortestServiceTime = 0;

    private long predictedHappinessSum = 0;
    private long averagePredictedHappiness = 0;

    private long actualHappinessSum = 0;
    private long averageActualHappiness = 0;

    private long easySum = 0;
    private long easyFraction = 0;
    private List<CallData> calls;

    public String getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(String simulationId) {
        this.simulationId = simulationId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public int getWorkers() {
        return workers;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public int getTotalCalls() {
        return totalCalls;
    }

    public void setTotalCalls(int totalCalls) {
        this.totalCalls = totalCalls;
    }

    public int getBouncedCalls() {
        return bouncedCalls;
    }

    public void setBouncedCalls(int bouncedCalls) {
        this.bouncedCalls = bouncedCalls;
    }

    public int getResolvedCalls() {
        return resolvedCalls;
    }

    public void setResolvedCalls(int resolvedCalls) {
        this.resolvedCalls = resolvedCalls;
    }

    public int getUnresolvedCalls() {
        return unresolvedCalls;
    }

    public void setUnresolvedCalls(int unresolvedCalls) {
        this.unresolvedCalls = unresolvedCalls;
    }

    public long getCallDelay() {
        return callDelay;
    }

    public void setCallDelay(long callDelay) {
        this.callDelay = callDelay;
    }

    public long getAverageWaitTime() {
        return averageWaitTime;
    }

    public void setAverageWaitTime(long averageWaitTime) {
        this.averageWaitTime = averageWaitTime;
    }

    public long getLongestWaitTime() {
        return longestWaitTime;
    }

    public void setLongestWaitTime(long longestWaitTime) {
        this.longestWaitTime = longestWaitTime;
    }

    public long getShortestWaitTime() {
        return shortestWaitTime;
    }

    public void setShortestWaitTime(long shortestWaitTime) {
        this.shortestWaitTime = shortestWaitTime;
    }

    public long getAveragePredictedHappiness() {
        return averagePredictedHappiness;
    }

    public void setAveragePredictedHappiness(long averagePredictedHappiness) {
        this.averagePredictedHappiness = averagePredictedHappiness;
    }

    public long getAverageActualHappiness() {
        return averageActualHappiness;
    }

    public void setAverageActualHappiness(long averageActualHappiness) {
        this.averageActualHappiness = averageActualHappiness;
    }

    public List<CallData> getCalls() {
        return calls;
    }

    public void setCalls(List<CallData> calls) {
        this.calls = calls;
    }

    public long getAverageServiceTime() {
        return averageServiceTime;
    }

    public void setAverageServiceTime(long averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public long getLongestServiceTime() {
        return longestServiceTime;
    }

    public void setLongestServiceTime(long longestServiceTime) {
        this.longestServiceTime = longestServiceTime;
    }

    public long getShortestServiceTime() {
        return shortestServiceTime;
    }

    public void setShortestServiceTime(long shortestServiceTime) {
        this.shortestServiceTime = shortestServiceTime;
    }

    public long getEasyFraction() {
        return easyFraction;
    }

    public void setEasyFraction(long easyFraction) {
        this.easyFraction = easyFraction;
    }

    public long getOverallWaitTime() {
        return overallWaitTime;
    }

    public void setOverallWaitTime(long overallWaitTime) {
        this.overallWaitTime = overallWaitTime;
    }

    public long getOverallServiceTime() {
        return overallServiceTime;
    }

    public void setOverallServiceTime(long overallServiceTime) {
        this.overallServiceTime = overallServiceTime;
    }

    public long getPredictedHappinessSum() {
        return predictedHappinessSum;
    }

    public void setPredictedHappinessSum(long predictedHappinessSum) {
        this.predictedHappinessSum = predictedHappinessSum;
    }

    public long getActualHappinessSum() {
        return actualHappinessSum;
    }

    public void setActualHappinessSum(long actualHappinessSum) {
        this.actualHappinessSum = actualHappinessSum;
    }

    public long getEasySum() {
        return easySum;
    }

    public void setEasySum(long easySum) {
        this.easySum = easySum;
    }
}
