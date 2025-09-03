package com.cropwise.cw_system.repositories;

import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
 // Optional<Crop> findByNameAndUserId(String crop, Long userId);
 //Optional<Crop> findByNameAndUserId(String cropName, Long userId);
 Optional<Crop> findByIdAndUser_Id(Long cropId, Long userId);
}

