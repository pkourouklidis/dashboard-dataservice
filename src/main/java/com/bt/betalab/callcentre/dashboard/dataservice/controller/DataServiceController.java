/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.controller;

import com.bt.betalab.callcentre.dashboard.dataservice.api.CallData;
import com.bt.betalab.callcentre.dashboard.dataservice.api.SimulationData;
import com.bt.betalab.callcentre.dashboard.dataservice.api.SimulationSummary;
import com.bt.betalab.callcentre.dashboard.dataservice.exceptions.DataServiceException;
import com.bt.betalab.callcentre.dashboard.dataservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DataServiceController {

    @Autowired
    DataService service;

    @PostMapping(produces = "application/json", value = "api/v1/report")
    public ResponseEntity reportCallData(@RequestBody CallData request)  {
        try {
            service.reportCallData(request);
            return ResponseEntity.ok().build();
        } catch (DataServiceException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(produces = "application/json", value = "api/v1/simulation/{id}")
    public ResponseEntity<SimulationData> getSimulationData(@PathVariable String id, @RequestParam Optional<Integer> count)  {
        try {
            if (service.simulationExists(id)) {
                return ResponseEntity.ok(service.getSimulationData(id, count));
            }
            return ResponseEntity.notFound().build();
        } catch (DataServiceException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(produces = "application/json", value = "api/v1/simulation")
    public ResponseEntity<List<Object>> getSimulations()  {
        try {
            return ResponseEntity.ok(service.getSimulations());
        } catch (DataServiceException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
