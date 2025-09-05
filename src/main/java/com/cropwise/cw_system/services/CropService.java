package com.cropwise.cw_system.services;

import com.cropwise.cw_system.dtos.crop.CropMapper;
import com.cropwise.cw_system.dtos.crop.CropRequest;
import com.cropwise.cw_system.dtos.crop.CropResponse;
import com.cropwise.cw_system.exception.CropNotFoundException;
import com.cropwise.cw_system.exception.ForbiddenUserRequest;
import com.cropwise.cw_system.exception.UsernameNotFoundException;
import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.models.IdealSensor;
import com.cropwise.cw_system.models.User;
import com.cropwise.cw_system.repositories.CropRepository;
import com.cropwise.cw_system.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CropService {
    private final CropRepository cropRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public CropService(CropRepository cropRepository, UserRepository userRepository, UserService userService) {
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<CropResponse>getAllCrops(Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication);
        List<Crop> crops;
        if (currentUser.getRole() == User.Role.ADMIN) {
            crops = cropRepository.findAll();
        } else {
            crops = cropRepository.findByUser_Id(currentUser.getId());
        }
        return crops
                .stream()
                .map(crop -> CropMapper.entityToDto(crop))
                .toList();
    }
    public CropResponse createCrop(CropRequest cropRequest, Authentication authentication){
        User currentUser = userService.getCurrentUser(authentication);
        Crop newCrop;

        if (currentUser.getRole() == User.Role.ADMIN) {
            User foundUser = userRepository.findByName(cropRequest.userName())
                    .orElseThrow(() -> new UsernameNotFoundException(cropRequest.userName()));
            newCrop = CropMapper.dtoToEntity(cropRequest, foundUser);
        } else {
            if (!Objects.equals(currentUser.getName(), cropRequest.userName())) {
                throw new ForbiddenUserRequest("Access Denied: You cannot create crops on others");
            }
            newCrop = CropMapper.dtoToEntity(cropRequest, currentUser);
        }
        Crop savedCrop = cropRepository.save(newCrop);
        return CropMapper.entityToDto(savedCrop);
    }

    public CropResponse getCropById(Long id, Authentication authentication) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id " + id));

        User currentUser = userService.getCurrentUser(authentication);

        if (currentUser.getRole() != User.Role.ADMIN &&
                !Objects.equals(crop.getUser().getId(), currentUser.getId())) {
            throw new ForbiddenUserRequest(
                    "Access denied: You can only view your own crops.");
        }

        return CropMapper.entityToDto(crop);
    }

    public CropResponse updateCrop(Long id, CropRequest cropRequest, Authentication authentication) {
        Crop actualCrop = cropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id " + id));

        User currentUser = userService.getCurrentUser(authentication);

        if (currentUser.getRole() != User.Role.ADMIN &&
                !Objects.equals(actualCrop.getUser().getId(), currentUser.getId())) {
            throw new ForbiddenUserRequest(
                    "Access denied: You can only update your own crops"
            );
        }
        actualCrop.setName(cropRequest.name());
        actualCrop.setDescription(cropRequest.description());

        Crop updatedCrop = cropRepository.save(actualCrop);
        return CropMapper.entityToDto(updatedCrop);
    }

    public void deleteCrop(Long id, Authentication authentication) {
        Crop currentCrop = cropRepository.findById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id " + id));

        User currentUser = userService.getCurrentUser(authentication);

        // Check authorization: deny if user is NOT admin AND does NOT own the crop
        if (currentUser.getRole() != User.Role.ADMIN &&
                !Objects.equals(currentCrop.getUser().getId(), currentUser.getId())) {
            throw new ForbiddenUserRequest(
                    "Access denied: You can only delete your own crops"
            );
        }

        cropRepository.delete(currentCrop);
    }


}
