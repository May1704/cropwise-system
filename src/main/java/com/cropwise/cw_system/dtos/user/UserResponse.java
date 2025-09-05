package com.cropwise.cw_system.dtos.user;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        com.cropwise.cw_system.models.User.Role role,
        List<String> crops
) {
}
