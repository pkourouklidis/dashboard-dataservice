/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.service;

import com.bt.betalab.callcentre.dashboard.dataservice.api.CallData;
import com.bt.betalab.callcentre.dashboard.dataservice.api.CustomerPrediction;
import com.bt.betalab.callcentre.dashboard.dataservice.api.SimulationData;
import com.bt.betalab.callcentre.dashboard.dataservice.api.SimulationSummary;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    @Autowired
    DataServiceConfig config;

    @Autowired
    CallRepository repo;

    @Autowired
    WebClientFactory clientFactory;

    public void reportCallData(CallData request) throws DataServiceException {
        WebClient webClient = clientFactory.generateWebClient(config.getAiServiceUrl());
        ResponseEntity<CustomerPrediction> reply = webClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .toEntity(CustomerPrediction.class)
                .block();

        if (!reply.getStatusCode().is2xxSuccessful()) {
            request.getCustomer().setPredictedToBeHappy(reply.getBody().isHappy());
            repo.save(request);
        }
        Logger.log("Failed to communicate with ai service backend. Error code: " + reply.getStatusCodeValue(), LogLevel.ERROR);
        throw new DataServiceException();
    }

    public SimulationData getSimulationData(String id, int count) throws DataServiceException {
        SimulationData simulationData = new SimulationData();
        List<CallData> calls = repo.findCallsForSimulation(id);
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
            if (call.isBounced()) {
                simulationData.setBouncedCalls(simulationData.getBouncedCalls()+1);
            } else if (call.isSolved()) {
                simulationData.setResolvedCalls(simulationData.getResolvedCalls()+1);
            } else {
                simulationData.setUnresolvedCalls(simulationData.getUnresolvedCalls()+1);
            }

            long waitTime = call.getPickupTime().getTime() - call.getArrivalTime().getTime();
            waitTimeSum += waitTime;
            if (simulationData.getLongestWaitTime() < waitTime ) {
                simulationData.setLongestWaitTime(waitTime);
            }
            if (simulationData.getShortestWaitTime() == 0 || simulationData.getShortestWaitTime() < waitTime ) {
                simulationData.setLongestWaitTime(waitTime);
            }

            long serviceTime = call.getClosingTime().getTime() - call.getPickupTime().getTime();
            serviceTimeSum += serviceTime;
            if (simulationData.getLongestServiceTime() < serviceTime ) {
                simulationData.setLongestServiceTime(serviceTime);
            }
            if (simulationData.getShortestServiceTime() == 0 || simulationData.getShortestServiceTime() < serviceTime ) {
                simulationData.setLongestServiceTime(serviceTime);
            }

            if (call.getCustomer().isPredictedToBeHappy()) {
                predictedHappySum++;
            }
            if (call.getCustomer().isHappy()) {
                actualHappySum++;
            }

            if (call.isEasy()) { easySum++; };
        }

        simulationData.setCalls(calls.subList(calls.size()-count-1, calls.size()-1));
        simulationData.setAverageWaitTime(waitTimeSum / (long)calls.size());
        simulationData.setAverageServiceTime(serviceTimeSum / (long)calls.size());
        simulationData.setAveragePredictedHappiness((long)predictedHappySum / (long)calls.size());
        simulationData.setAverageActualHappiness((long)actualHappySum / (long)calls.size());
        simulationData.setEasyFraction((long)easySum / (long)calls.size());
        return simulationData;
    }

    public List<SimulationSummary> getSimulations() throws DataServiceException {
        List<CallData> firstCalls = repo.findFirstSimulationCalls();
        List<SimulationSummary> summaries = new ArrayList<>();

        for (CallData call: firstCalls) {
            SimulationSummary summary = new SimulationSummary();
            summary.setSimulationId(call.getSimulationId());
            summary.setSimulationStartTime(call.getSimulationStartTime());
            summaries.add(summary);
        }

        return summaries;
    }

    public boolean simulationExists(String id) {
        return repo.existsById(id);
    }
}
