package com.cropwise.cw_system.dtos.user;

import com.cropwise.cw_system.models.Crop;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        String role,
        List<String> crops
) {
}
