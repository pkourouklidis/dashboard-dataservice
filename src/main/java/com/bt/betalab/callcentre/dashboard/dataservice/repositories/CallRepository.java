/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 11/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/

package com.bt.betalab.callcentre.dashboard.dataservice.repositories;

import com.bt.betalab.callcentre.dashboard.dataservice.api.CallData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends CrudRepository<CallData, String> {

    @Query("select cd from CallData cd")
    public List<CallData> findAllCalls();
    
    public List<CallData> findCallsBySimulationIdOrderByArrivalTimeAsc(String simulationId);

    public boolean existsBySimulationId(String id);
}
