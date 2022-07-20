/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.service;

import com.bt.betalab.callcentre.dashboard.dataservice.api.*;
import com.bt.betalab.callcentre.dashboard.dataservice.config.DataServiceConfig;
import com.bt.betalab.callcentre.dashboard.dataservice.exceptions.DataServiceException;
import com.bt.betalab.callcentre.dashboard.dataservice.logging.LogLevel;
import com.bt.betalab.callcentre.dashboard.dataservice.logging.Logger;
import com.bt.betalab.callcentre.dashboard.dataservice.messaging.WebClientFactory;
import com.bt.betalab.callcentre.dashboard.dataservice.repositories.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.*;

@Service
public class DataService {
    @Autowired
    DataServiceConfig config;

    @Autowired
    CallRepository repo;

    @Autowired
    WebClientFactory clientFactory;

    public void reportCallData(CallData request) throws DataServiceException {
        Duration waitDuration = Duration.between(request.getArrivalTime(), request.getPickupTime());
        Duration serviceDuration = Duration.between(request.getPickupTime(), request.getClosingTime());
        CustomerPredictionRequest predictionRequest = new CustomerPredictionRequest(waitDuration.getSeconds(), serviceDuration.getSeconds());
        WebClient webClient = clientFactory.generateWebClient(config.getAiServiceUrl());
        ResponseEntity<CustomerPrediction> reply = webClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(predictionRequest)
                .retrieve()
                .toEntity(CustomerPrediction.class)
                .block();

        if (!reply.getStatusCode().is2xxSuccessful()) {
            Logger.log("Failed to communicate with ai service backend. Error code: " + reply.getStatusCodeValue(), LogLevel.ERROR);
            throw new DataServiceException();
        }
        request.getCustomer().setIsPredictedToBeHappy(reply.getBody().isHappy());
        repo.save(request);
    }

    public SimulationData getSimulationData(String id, Optional<Integer> count) throws DataServiceException {
        SimulationData simulationData = new SimulationData();
        List<CallData> calls = repo.findCallsBySimulationIdOrderByArrivalTimeAsc(id);
        long waitTimeSum = 0;
        long serviceTimeSum = 0;
        int predictedHappySum = 0;
        int actualHappySum = 0;
        int easySum = 0;

        simulationData.setSimulationId(id);
        simulationData.setStartTime(calls.get(0).getSimulationStartTime());
        simulationData.setCallDelay(calls.get(0).getCallDelay());
        simulationData.setTotalCalls(calls.size());
        simulationData.setWorkers(calls.get(0).getWorkers());

        for (CallData call: calls) {
            if (call.getIsBounced()) {
                simulationData.setBouncedCalls(simulationData.getBouncedCalls()+1);
            } else if (call.getIsSolved()) {
                simulationData.setResolvedCalls(simulationData.getResolvedCalls()+1);
            } else {
                simulationData.setUnresolvedCalls(simulationData.getUnresolvedCalls()+1);
            }

            long waitTime = Duration.between(call.getArrivalTime(), call.getPickupTime()).getSeconds();
            waitTimeSum += waitTime;
            if (simulationData.getLongestWaitTime() < waitTime ) {
                simulationData.setLongestWaitTime(waitTime);
            }
            if (simulationData.getShortestWaitTime() == 0 || simulationData.getShortestWaitTime() < waitTime ) {
                simulationData.setShortestWaitTime(waitTime);
            }

            long serviceTime = Duration.between(call.getPickupTime(), call.getClosingTime()).getSeconds();
            serviceTimeSum += serviceTime;
            if (simulationData.getLongestServiceTime() < serviceTime ) {
                simulationData.setLongestServiceTime(serviceTime);
            }
            if (simulationData.getShortestServiceTime() == 0 || simulationData.getShortestServiceTime() < serviceTime ) {
                simulationData.setShortestServiceTime(serviceTime);
            }

            if (call.getCustomer().getIsPredictedToBeHappy()) {
                predictedHappySum++;
            }
            if (call.getCustomer().getIsHappy()) {
                actualHappySum++;
            }

            if (call.getIsEasy()) { easySum++; };
        }

        if (count.isPresent()) {
            if (count.get() < calls.size()) {
                simulationData.setCalls(calls.subList(calls.size() - count.get() - 1, calls.size() - 1));
            }
            else {
                simulationData.setCalls(calls);
            }
        } else {
            simulationData.setCalls(calls);
        }
        simulationData.setAverageWaitTime(waitTimeSum / (long)calls.size());
        simulationData.setAverageServiceTime(serviceTimeSum / (long)calls.size());
        simulationData.setAveragePredictedHappiness((long)predictedHappySum / (long)calls.size());
        simulationData.setAverageActualHappiness((long)actualHappySum / (long)calls.size());
        simulationData.setEasyFraction((long)easySum / (long)calls.size());
        return simulationData;
    }

    public List<SimulationSummary> getSimulations() throws DataServiceException {
        List<CallData> allCalls = repo.findAllCalls();
        List<SimulationSummary> summaries = new ArrayList<>();
        Set<String> uniqueIds = new HashSet<>();

        for (CallData call: allCalls) {
            if (!uniqueIds.contains(call.getSimulationId())) {
                SimulationSummary summary = new SimulationSummary();
                summary.setSimulationId(call.getSimulationId());
                summary.setSimulationStartTime(call.getSimulationStartTime());
                summaries.add(summary);
                uniqueIds.add(call.getSimulationId());
            }
        }

        return summaries;
    }

    public boolean simulationExists(String id) {
        return repo.existsBySimulationId(id);
    }
}
