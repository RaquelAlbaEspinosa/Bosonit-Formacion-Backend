package com.bosonit.formacion.block17springBatch.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class WeatherRisk {
    @Id
    @GeneratedValue
    private int weatherRiskId;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Weather weather;
    @Enumerated(EnumType.STRING)
    private Risk risk;
    private Date predictionDate;

    public WeatherRisk(Weather weather) {
        this.weather = weather;
        this.predictionDate = weather.getDate();
        if(this.weather.getTemperature() > 36) {
            this.risk = Risk.HIGH;
        } else if(this.weather.getTemperature() > 32 && weather.getTemperature() <= 36){
            this.risk = Risk.MID;
        } else if(this.weather.getTemperature() < -24){
            this.risk = Risk.LOW;
        } else {
            this.risk = Risk.NORISK;
        }
    }

    public Date getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(Date predictionDate) {
        this.predictionDate = predictionDate;
    }

    public int getWeatherRiskId() {
        return weatherRiskId;
    }

    public void setWeatherRiskId(int weatherRiskId) {
        this.weatherRiskId = weatherRiskId;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }
}
