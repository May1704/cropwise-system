package com.cropwise.cw_system.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ideal sensors")
public class IdealSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private double temperature_min;
    private double temperature_max;
    private double humidity_min;
    private double humidity_max;
    private double conductivity_min;
    private double conductivity_max;
    private double ph_min;
    private double ph_max;

    @OneToMany(mappedBy = "idealSensor")
    private List<Crop> crops = new ArrayList<>();

    private IdealSensor(){
    }

    public IdealSensor(double temperature_min,
    double temperature_max, double humidity_min,
    double humidity_max, double ph_min, double ph_max, double conductivity_min, double conductivity_max){
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
        this.humidity_min = humidity_min;
        this.humidity_max = humidity_max;
        this.conductivity_min = conductivity_min;
        this.conductivity_max = conductivity_max;
        this.ph_min = ph_min;
        this.ph_max = ph_max;
    }

    public boolean isTemperatureInRange(double temperature) {
        return temperature >= temperature_min && temperature <= temperature_max;
    }

    public boolean isHumidityInRange(double humidity) {
        return humidity >= humidity_min && humidity <= humidity_max;
    }

    public boolean isConductivityInRange(double conductivity) {
        return conductivity >= conductivity_min && conductivity <= conductivity_max;
    }

    public boolean isPhInRange(double ph) {
        return ph >= ph_min && ph <= ph_max;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public double getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(double temperature_min) {
        this.temperature_min = temperature_min;
    }

    public double getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(double temperature_max) {
        this.temperature_max = temperature_max;
    }

    public double getHumidity_min() {
        return humidity_min;
    }

    public void setHumidity_min(double humidity_min) {
        this.humidity_min = humidity_min;
    }

    public double getPh_min() {
        return ph_min;
    }

    public void setPh_min(double ph_min) {
        this.ph_min = ph_min;
    }

    public double getHumidity_max() {
        return humidity_max;
    }

    public void setHumidity_max(double humidity_max) {
        this.humidity_max = humidity_max;
    }

    public double getPh_max() {
        return ph_max;
    }

    public void setPh_max(double ph_max) {
        this.ph_max = ph_max;
    }

    public double getConductivity_max() {
        return conductivity_max;
    }

    public void setConductivity_max(double conductivity_max) {
        this.conductivity_max = conductivity_max;
    }

    public double getConductivity_min() {
        return conductivity_min;
    }

    public void setConductivity_min(double conductivity_min) {
        this.conductivity_min = conductivity_min;
    }

    public List<Crop> getCrops() {
        return crops;
    }

    public void setCrops(List<Crop> crops) {
        this.crops = crops;
    }
}
