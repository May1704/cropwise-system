package com.cropwise.cw_system.dtos.crop;

import com.cropwise.cw_system.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CropRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 12, message = "Name must contain min 2 and max 12 characters")
        String name,
        @Size(max = 150, message = "Description must contain max 150 characters")
        String description,
        String userName
) {
}
