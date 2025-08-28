package com.cropwise.cw_system.models;

import jakarta.persistence.*;

@Entity
@Table(name="simulatedsensors")
public class SimulatedSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double ph;
    private double conductivity;
    private double humidity;
    private double temperature;

    public SimulatedSensor(){

    }

    public SimulatedSensor(double temperature, double humidity, double conductivity, double ph) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.conductivity = conductivity;
        this.ph = ph;
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
}
