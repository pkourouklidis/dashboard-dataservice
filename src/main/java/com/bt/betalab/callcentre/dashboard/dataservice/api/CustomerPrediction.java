/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import java.util.ArrayList;
import java.util.List;

public class CustomerPrediction {
    List<PredictionResult> outputs = new ArrayList<>();

    public void setOutputs(List<PredictionResult> outputs) {
        this.outputs = outputs;
    }

    public boolean isHappy() {
        return outputs.get(0).isHappy();
    }
}
