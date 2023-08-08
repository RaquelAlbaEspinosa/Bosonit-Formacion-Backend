package com.bosonit.formacion.block17springBatch.error;

import java.util.Date;

public class ErrorRecord {
    private String city;
    private Date date;
    private int temperature;

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

    public ErrorRecord(String city, Date date, int temperature) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return this.city + "," + this.date + "," + this.temperature;
    }
}
