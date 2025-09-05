package com.cropwise.cw_system.models;

import com.cropwise.cw_system.exception.ValueOutOfRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "simulated sensors")
public class SimulatedSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperature;
    private double humidity;
    private double conductivity;
    private double ph;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    //private LocalDateTime created;

    public SimulatedSensor(){
    }

    public SimulatedSensor(double temperature, double humidity, double conductivity, double ph, Crop crop) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.conductivity = conductivity;
        this.ph = ph;
        this.crop = crop;
        validateAgainstIdealSensor();
    }

    private void validateAgainstIdealSensor() {
        if (crop == null || crop.getIdealSensor() == null) {
            throw new ValueOutOfRange("Crop must have an associated IdealSensor for validation");
        }

        IdealSensor idealSensor = crop.getIdealSensor();

        if (!idealSensor.isTemperatureInRange(this.temperature)) {
            throw new ValueOutOfRange(
                    String.format("Temperature %.2f is outside ideal range [%.2f, %.2f]",
                            this.temperature, idealSensor.getTemperature_min(), idealSensor.getTemperature_max())
            );
        }

        if (!idealSensor.isHumidityInRange(this.humidity)) {
            throw new ValueOutOfRange(
                    String.format("Humidity %.2f is outside ideal range [%.2f, %.2f]",
                            this.humidity, idealSensor.getHumidity_min(), idealSensor.getHumidity_max())
            );
        }

        if (!idealSensor.isConductivityInRange(this.conductivity)) {
            throw new ValueOutOfRange(
                    String.format("Conductivity %.2f is outside ideal range [%.2f, %.2f]",
                            this.conductivity, idealSensor.getConductivity_min(), idealSensor.getConductivity_max())
            );
        }

        if (!idealSensor.isPhInRange(this.ph)) {
            throw new ValueOutOfRange(
                    String.format("pH %.2f is outside ideal range [%.2f, %.2f]",
                            this.ph, idealSensor.getPh_min(), idealSensor.getPh_max())
            );
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        if (crop != null && crop.getIdealSensor() != null) {
            validateAgainstIdealSensor();
        }
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
        if (crop != null && crop.getIdealSensor() != null) {
            validateAgainstIdealSensor();
        }
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
        if (crop != null && crop.getIdealSensor() != null) {
            validateAgainstIdealSensor();
        }
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
        if (crop != null && crop.getIdealSensor() != null) {
            validateAgainstIdealSensor();
        }
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
        if (crop != null && crop.getIdealSensor() != null) {
            validateAgainstIdealSensor();
        }
    }
}