package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.crop.CropRequest;
import com.cropwise.cw_system.dtos.crop.CropResponse;
import com.cropwise.cw_system.services.CropService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/crops/{id}")
    public ResponseEntity<CropResponse> getCropById(@PathVariable Long id) {
        return new ResponseEntity<>(cropService.getCropById(id),HttpStatus.OK);
    }

    @PutMapping("crops/{id}")
    public ResponseEntity<CropResponse> updateCrop(@PathVariable Long id, @Valid @RequestBody CropRequest cropRequest) {
        return new ResponseEntity<>(cropService.updateCrop(id, cropRequest), HttpStatus.OK);
    }

    @DeleteMapping("/crops/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

