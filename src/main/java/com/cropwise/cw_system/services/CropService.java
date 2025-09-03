package com.cropwise.cw_system.services;

import com.cropwise.cw_system.dtos.crop.CropMapper;
import com.cropwise.cw_system.dtos.crop.CropRequest;
import com.cropwise.cw_system.dtos.crop.CropResponse;
import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.models.User;
import com.cropwise.cw_system.repositories.CropRepository;
import com.cropwise.cw_system.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {
    private final CropRepository cropRepository;
    private final UserRepository userRepository;

    public CropService(CropRepository cropRepository, UserRepository userRepository) {
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
    }

    public List<CropResponse>getAllCrops() {
        List<Crop> crops = cropRepository.findAll();
        return crops
                .stream()
                .map(crop -> CropMapper.entityToDto(crop))
                .toList();
    }

    public CropResponse createCrop(CropRequest cropRequest){
        User foundUser = userRepository.findByName(cropRequest.userName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Crop newCrop = CropMapper.dtoToEntity(cropRequest,foundUser);
        //newCrop.setUser(foundUser);
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
