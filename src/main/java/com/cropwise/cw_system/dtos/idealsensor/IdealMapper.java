package com.cropwise.cw_system.dtos.idealsensor;

import com.cropwise.cw_system.dtos.user.UserRequest;
import com.cropwise.cw_system.models.IdealSensor;

import java.util.List;


public class IdealMapper {

    public static IdealSensor dtoToEntity (IdealRequest dtoIdealRequest){
        return new IdealSensor(dtoIdealRequest.temperature_min(),dtoIdealRequest.temperature_max(),
                dtoIdealRequest.humidity_min(), dtoIdealRequest.humidity_max(), dtoIdealRequest.conductivity_min(), dtoIdealRequest.conductivity_max(),
                dtoIdealRequest.ph_min(),dtoIdealRequest.ph_max());
    }

    public static IdealResponse entityToDto (IdealSensor idealSensor){
        List<String> crops = idealSensor.getCrops().stream()
                .map(crop -> crop.getName())
                .toList();
        return new IdealResponse(idealSensor.getTemperature_min(), idealSensor.getTemperature_max(),
                idealSensor.getHumidity_min(), idealSensor.getHumidity_max(), idealSensor.getConductivity_min(), idealSensor.getConductivity_max(),
                idealSensor.getPh_min(), idealSensor.getPh_max(), crops);
    }
}
