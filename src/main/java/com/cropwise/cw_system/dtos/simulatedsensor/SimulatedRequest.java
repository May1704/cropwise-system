package com.cropwise.cw_system.dtos.simulatedsensor;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record SimulatedRequest(
        @DecimalMin(value = "0.0", message = "The minimum temperature allowed is 0.0 ºC")
        @DecimalMax(value = "60.0", message = "The maximum temperature allowed is 60.0 ºC")
        double temperature,
        @DecimalMin(value = "0.0", message = "The minimum humidity allowed is 0.0%")
        @DecimalMax(value = "100.0", message = "The maximum humidity allowed is 100.0%")
        double humidity,
        @DecimalMin(value = "0.0", message = "The minimum conductivity allowed is 0.0mS/cm")
        double conductivity,
        @DecimalMin(value = "0.0", message = "The minimum pH allowed is 0.0")
        @DecimalMax(value = "14.0", message = "The maximum pH allowed is 14.0")
        double ph,
        Long cropId,
        //String cropName,
        Long userId

) {
}
