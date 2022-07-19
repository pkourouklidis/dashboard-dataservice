/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 18/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import java.util.ArrayList;
import java.util.List;

public class CustomerPredictionRequest {

    private String id = "1";
    private List<PredictionInput> inputs = new ArrayList<>();

    private List<PredictionOutput> outputs = new ArrayList<>();


    public CustomerPredictionRequest(long waitDuration, long serviceDuration) {
        PredictionInput input = new PredictionInput((int)waitDuration, (int)serviceDuration);
        inputs.add(input);

        PredictionOutput output = new PredictionOutput();
        outputs.add(output);
    }

    public String getId() {
        return id;
    }

    public List<PredictionInput> getInputs() {
        return inputs;
    }

    public List<PredictionOutput> getOutputs() {
        return outputs;
    }
}
