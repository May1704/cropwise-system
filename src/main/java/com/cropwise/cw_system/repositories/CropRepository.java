package com.cropwise.cw_system.repositories;

import com.cropwise.cw_system.models.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
