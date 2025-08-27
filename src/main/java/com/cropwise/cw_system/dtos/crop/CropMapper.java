package com.cropwise.cw_system.dtos.crop;

import com.cropwise.cw_system.models.Crop;

public class CropMapper {

    public static Crop dtoToEntity(CropRequest dtoCropRequest) {
        return new Crop(dtoCropRequest.name(), dtoCropRequest.description());
    }

    public static CropResponse entityToDto(Crop crop) {
        return new CropResponse(crop.getName(), crop.getDescription());
    }
}