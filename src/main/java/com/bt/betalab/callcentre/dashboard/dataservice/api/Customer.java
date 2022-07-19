/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 06/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "customer")
    private CallData callData;

    private boolean isHappyToWait;
    private boolean isHappyToWaitForService;
    private boolean isUnderstanding;
    private boolean isHappy;

    private boolean isPredictedToBeHappy;

    public boolean getIsHappyToWait() {
        return isHappyToWait;
    }

    public void setIsHappyToWait(boolean happyToWait) {
        isHappyToWait = happyToWait;
    }

    public boolean getIsHappyToWaitForService() {
        return isHappyToWaitForService;
    }

    public void setIsHappyToWaitForService(boolean happyToWaitForService) {
        isHappyToWaitForService = happyToWaitForService;
    }

    public boolean getIsUnderstanding() {
        return isUnderstanding;
    }

    public void setIsUnderstanding(boolean understanding) {
        isUnderstanding = understanding;
    }

    public boolean getIsHappy() {
        return isHappy;
    }

    public void setIsHappy(boolean happy) {
        isHappy = happy;
    }

    public boolean getIsPredictedToBeHappy() {
        return isPredictedToBeHappy;
    }

    public void setIsPredictedToBeHappy(boolean predictedToBeHappy) {
        isPredictedToBeHappy = predictedToBeHappy;
    }
}
