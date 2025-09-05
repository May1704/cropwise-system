package com.cropwise.cw_system.controllers;

import com.cropwise.cw_system.dtos.crop.CropRequest;
import com.cropwise.cw_system.dtos.crop.CropResponse;
import com.cropwise.cw_system.services.CropService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<List<CropResponse>> getAllCrops(Authentication authentication) {
        return new ResponseEntity<>(cropService.getAllCrops(authentication), HttpStatus.OK);
    }
    @PostMapping("/crops")
    public ResponseEntity<CropResponse> createCrop(@Valid @RequestBody CropRequest cropRequest, Authentication authentication) {
        return new ResponseEntity<>(cropService.createCrop(cropRequest,authentication), HttpStatus.CREATED);
    }

    @GetMapping("/crops/{id}")
    public ResponseEntity<CropResponse> getCropById(@PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(cropService.getCropById(id,authentication),HttpStatus.OK);
    }

    @PutMapping("crops/{id}")
    public ResponseEntity<CropResponse> updateCrop(@PathVariable Long id, @Valid @RequestBody CropRequest cropRequest, Authentication authentication) {
        return new ResponseEntity<>(cropService.updateCrop(id, cropRequest, authentication), HttpStatus.OK);
    }

    @DeleteMapping("/crops/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id, Authentication authentication) {
        cropService.deleteCrop(id, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}

