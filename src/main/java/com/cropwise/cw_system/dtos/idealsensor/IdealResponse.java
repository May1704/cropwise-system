package com.cropwise.cw_system.dtos.idealsensor;

import java.util.List;

public record IdealResponse(
        double temperature_min,
        double temperature_max,
        double humidity_min,
        double humidity_max,
        double ph_min,
        double ph_max,
        double conductivity_min,
        double conductivity_max,
        List<String> crops
) {
}
