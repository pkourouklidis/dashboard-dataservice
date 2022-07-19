/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 06/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.api;

import javax.persistence.*;

@Entity
@Table(name="workerdetails")
public class WorkerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "workerDetails")
    private CallData callData;

    private boolean isSkilled;
    private boolean isFast;

    public WorkerDetails(boolean isSkilled, boolean isFast) {
        this.isSkilled = isSkilled;
        this.isFast = isFast;
    }

    public WorkerDetails() {
    }

    public boolean getIsSkilled() {
        return isSkilled;
    }

    public void setIsSkilled(boolean skilled) {
        isSkilled = skilled;
    }

    public boolean getIsFast() {
        return isFast;
    }

    public void setIsFast(boolean fast) {
        isFast = fast;
    }
}
