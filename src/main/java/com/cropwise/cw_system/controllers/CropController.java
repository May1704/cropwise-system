package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.crop.CropRequest;
import com.cropwise.cw_system.dtos.crop.CropResponse;
import com.cropwise.cw_system.services.CropService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CropController {
    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping("/crops")
    public ResponseEntity<List<CropResponse>> getAllCrops() {
        return new ResponseEntity<>(cropService.getAllCrops(), HttpStatus.OK);
    }
    @PostMapping("/crops")
    public ResponseEntity<CropResponse> createCrop(@Valid @RequestBody CropRequest cropRequest) {
        return new ResponseEntity<>(cropService.createCrop(cropRequest), HttpStatus.CREATED);
    }



}

