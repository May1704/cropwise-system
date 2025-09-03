package com.cropwise.cw_system.dtos.crop;

import com.cropwise.cw_system.models.Crop;
import com.cropwise.cw_system.models.User;

public class CropMapper {

    public static Crop dtoToEntity(CropRequest dtoCropRequest, User user) {
        return new Crop(dtoCropRequest.name(), dtoCropRequest.description(),user);
    }

    public static CropResponse entityToDto(Crop crop) {
        String user = (crop.getUser() != null) ? crop.getUser().getName() : null;
        return new CropResponse(crop.getName(), crop.getDescription(), user);
    }
}