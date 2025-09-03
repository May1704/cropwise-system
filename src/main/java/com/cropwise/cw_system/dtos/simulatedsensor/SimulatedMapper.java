package com.cropwise.cw_system.dtos.simulatedsensor;

import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.models.SimulatedSensor;

public class SimulatedMapper {

    public static SimulatedSensor dtoToEntity (SimulatedRequest dtoSimulatedRequest, Crop crop){
        return new SimulatedSensor(
                dtoSimulatedRequest.temperature(),
                dtoSimulatedRequest.humidity(),
                dtoSimulatedRequest.conductivity(),
                dtoSimulatedRequest.ph(),
                crop);
    }

    public static SimulatedResponse entityToDto (SimulatedSensor simulatedSensor){
        String crop = (simulatedSensor.getCrop() != null) ? simulatedSensor.getCrop().getName() : "No Crop Assigned";
        String userName = (simulatedSensor.getCrop() != null && simulatedSensor.getCrop().getUser() != null)
                ? simulatedSensor.getCrop().getUser().getName() : "No User Assigned";
        //String cropId = (simulatedSensor.getCrop() != null) ? simulatedSensor.getCrop().getId().toString() : "No Crop Assigned";
        return new SimulatedResponse(
                simulatedSensor.getTemperature(),
                simulatedSensor.getHumidity(),
                simulatedSensor.getConductivity(),
                simulatedSensor.getPh(),
                crop,
                userName
                //cropId
        );
    }
}

