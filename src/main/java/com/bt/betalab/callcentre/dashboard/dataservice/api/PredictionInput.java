/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 18/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

public class PredictionInput {
    private String name = "input";
    private int[] shape  = {1, 2};
    private String datatype = "INT32";
    private int[] data = new int[2];

    public PredictionInput(int waitDuration, int serviceDuration) {
        data[0] = waitDuration;
        data[1] = serviceDuration;
    }

    public String getName() {
        return name;
    }

    public int[] getShape() {
        return shape;
    }

    public String getDatatype() {
        return datatype;
    }

    public int[] getData() {
        return data;
    }
}
