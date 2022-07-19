/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 18/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import java.util.List;

public class PredictionResult {
    private List<Integer> data;

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public boolean isHappy() {
        return data.get(0).equals(1);
    }
}
