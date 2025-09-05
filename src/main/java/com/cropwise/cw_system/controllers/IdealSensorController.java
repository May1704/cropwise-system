package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.idealsensor.IdealResponse;
import com.cropwise.cw_system.dtos.simulatedsensor.SimulatedResponse;
import com.cropwise.cw_system.services.IdealSensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IdealSensorController {
    private final IdealSensorService idealSensorService;

    public IdealSensorController(IdealSensorService idealSensorService){
        this.idealSensorService = idealSensorService;
    }

    @GetMapping("/ideals")
    public ResponseEntity<List<IdealResponse>> getAllIdeals(){
        return new ResponseEntity<>(idealSensorService.getAllIdeals(), HttpStatus.OK);
    }
}

