package com.cropwise.cw_system.services;

import com.cropwise.cw_system.dtos.idealsensor.IdealMapper;
import com.cropwise.cw_system.dtos.idealsensor.IdealResponse;
import com.cropwise.cw_system.models.IdealSensor;
import com.cropwise.cw_system.repositories.IdealSensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdealSensorService {
    private final IdealSensorRepository idealSensorRepository;

    public IdealSensorService(IdealSensorRepository idealSensorRepository){
        this.idealSensorRepository = idealSensorRepository;
    }

    public List<IdealResponse> getAllIdeals() {
        List<IdealSensor> idealSensors = idealSensorRepository.findAll();
        return idealSensors
                .stream()
                .map(idealSensor -> IdealMapper.entityToDto(idealSensor))
                .toList();
    }

}
