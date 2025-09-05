package com.cropwise.cw_system.dtos.simulatedsensor;

public record SimulatedResponse(
        double temperature,
        double humidity,
        double conductivity,
        double ph,
        String cropName,
        String userName
) {
}
