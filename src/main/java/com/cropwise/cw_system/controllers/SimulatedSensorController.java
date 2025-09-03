package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.simulatedsensor.SimulatedRequest;
import com.cropwise.cw_system.dtos.simulatedsensor.SimulatedResponse;
import com.cropwise.cw_system.services.SimulatedSensorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimulatedSensorController {
    private final SimulatedSensorService simulatedSensorService;

    public SimulatedSensorController(SimulatedSensorService simulatedSensorService){
        this.simulatedSensorService = simulatedSensorService;
    }

    @GetMapping("/simulations")
    public ResponseEntity<List<SimulatedResponse>> getAllSimulateds(){
        return new ResponseEntity<>(simulatedSensorService.getAllSimulateds(), HttpStatus.OK);
    }

    @PostMapping("/simulations")
    public ResponseEntity<SimulatedResponse> createdSimulated(@Valid @RequestBody SimulatedRequest simulatedRequest) {
        return new ResponseEntity<>(simulatedSensorService.createdSimulated(simulatedRequest), HttpStatus.CREATED);
    }

    @PutMapping("/simulations/{id}")
    public ResponseEntity<SimulatedResponse> updateSimulatedSensor(
            @PathVariable Long id, @RequestBody SimulatedRequest simulatedRequest) {
        SimulatedResponse updatedResponse = simulatedSensorService.updateSimulatedSensor(id, simulatedRequest);
        return ResponseEntity.ok(updatedResponse);
    }

    @GetMapping("/simulations/{id}")
    public ResponseEntity<SimulatedResponse> getSimulatedSensorById(@PathVariable Long id) {
        SimulatedResponse simulatedResponse = simulatedSensorService.getSimulatedSensorById(id);
        return ResponseEntity.ok(simulatedResponse);
    }

    @DeleteMapping("/simulations/{id}")
    public ResponseEntity<Void> deleteSimulatedSensor(@PathVariable Long id) {
        simulatedSensorService.deleteSimulatedSensor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
