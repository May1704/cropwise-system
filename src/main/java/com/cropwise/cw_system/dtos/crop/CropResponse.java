package com.cropwise.cw_system.dtos.crop;

import com.cropwise.cw_system.models.User;

public record CropResponse(
        String name,
        String description,
        String userName
) {
}
