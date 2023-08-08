package com.bosonit.formacion.block17springBatch.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Weather {
    @Id
    @GeneratedValue
    private int weatherId;
    private String city;
    private Date date;
    private int temperature;
    @OneToOne
    private WeatherRisk weatherRisk;
    private boolean isValid = true;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public WeatherRisk getWeatherRisk() {
        return weatherRisk;
    }

    public void setWeatherRisk(WeatherRisk weatherRisk) {
        this.weatherRisk = weatherRisk;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Weather(int weatherId, String city, Date date, int temperature, WeatherRisk weatherRisk) {
        this.weatherId = weatherId;
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.weatherRisk = weatherRisk;
    }
    public Weather(){}
}
