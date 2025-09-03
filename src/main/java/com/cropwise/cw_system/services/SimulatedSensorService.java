package com.cropwise.cw_system.services;

import com.cropwise.cw_system.dtos.simulatedsensor.SimulatedMapper;
import com.cropwise.cw_system.dtos.simulatedsensor.SimulatedRequest;
import com.cropwise.cw_system.dtos.simulatedsensor.SimulatedResponse;
import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.models.SimulatedSensor;
import com.cropwise.cw_system.repositories.CropRepository;
import com.cropwise.cw_system.repositories.SimulatedSensorRepository;
import com.cropwise.cw_system.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulatedSensorService {
    private final SimulatedSensorRepository simulatedSensorRepository;
    private final CropRepository cropRepository;
    private final UserRepository userRepository;

    public SimulatedSensorService(SimulatedSensorRepository simulatedSensorRepository, CropRepository cropRepository, UserRepository userRepository) {
        this.simulatedSensorRepository = simulatedSensorRepository;
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;

    }

    public List<SimulatedResponse> getAllSimulateds() {
        List<SimulatedSensor> simulatedSensors = simulatedSensorRepository.findAll();
        return simulatedSensors
                .stream()
                .map(simulatedSensor -> SimulatedMapper.entityToDto(simulatedSensor))
                .toList();
    }

    public SimulatedResponse createdSimulated(SimulatedRequest simulatedRequest) {
        Crop foundCrop = cropRepository.findByIdAndUser_Id(simulatedRequest.cropId(),simulatedRequest.userId()).orElseThrow(() -> new IllegalArgumentException("Crop not found"));
        SimulatedSensor newSimulatedSensor = SimulatedMapper.dtoToEntity(simulatedRequest, foundCrop);
        SimulatedSensor saveSimulatedSensor = simulatedSensorRepository.save(newSimulatedSensor);
        return SimulatedMapper.entityToDto(saveSimulatedSensor);

    }

    public SimulatedResponse updateSimulatedSensor(Long id, SimulatedRequest simulatedRequest) {
        SimulatedSensor existingSensor = simulatedSensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SimulatedSensor not found with ID: " + id));
        existingSensor.setTemperature(simulatedRequest.temperature());
        existingSensor.setHumidity(simulatedRequest.humidity());
        existingSensor.setConductivity(simulatedRequest.conductivity());
        existingSensor.setPh(simulatedRequest.ph());
        SimulatedSensor savedSensor = simulatedSensorRepository.save(existingSensor);
        return SimulatedMapper.entityToDto(savedSensor);
    }

    public SimulatedResponse getSimulatedSensorById(Long id) {
        SimulatedSensor simulatedSensor = simulatedSensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SimulatedSensor not found with ID: " + id));
        return SimulatedMapper.entityToDto(simulatedSensor);
    }

    public void deleteSimulatedSensor(Long id) {
        SimulatedSensor existingSensor = simulatedSensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SimulatedSensor not found with ID: " + id));
        simulatedSensorRepository.delete(existingSensor);
    }

}
