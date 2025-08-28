package com.cropwise.cw_system.services;

import com.cropwise.cw_system.dtos.crop.CropMapper;
import com.cropwise.cw_system.dtos.crop.CropRequest;
import com.cropwise.cw_system.dtos.crop.CropResponse;
import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {
    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public List<CropResponse>getAllCrops() {
        List<Crop> crops = cropRepository.findAll();
        return crops
                .stream()
                .map(crop -> CropMapper.entityToDto(crop))
                .toList();
    }

    public CropResponse createCrop(CropRequest cropRequest){
        Crop newCrop = CropMapper.dtoToEntity(cropRequest);
        Crop savedCrop = cropRepository.save(newCrop);
        return CropMapper.entityToDto(savedCrop);
    }

    public CropResponse getCropById (Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crop not found with id " + id));
        return CropMapper.entityToDto(crop);
    }

    public CropResponse updateCrop (Long id, CropRequest cropRequest) {
        Crop actualCrop = cropRepository.findById(id).orElseThrow(() -> new RuntimeException("Crop not found with id" + id));

        actualCrop.setName(cropRequest.name());
        actualCrop.setDescription(cropRequest.description());

        Crop updateCrop = cropRepository.save(actualCrop);
        return CropMapper.entityToDto(updateCrop);
    }

    public void deleteCrop (Long id) {
        Crop currentCrop = cropRepository.findById(id).orElseThrow(() -> new RuntimeException("Crop not found with id " + id));
        cropRepository.delete(currentCrop);
    }


}
