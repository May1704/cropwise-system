package com.cropwise.cw_system.models;

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
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
