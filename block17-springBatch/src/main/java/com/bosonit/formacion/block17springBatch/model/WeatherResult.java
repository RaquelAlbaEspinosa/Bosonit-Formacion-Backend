package com.bosonit.formacion.block17springBatch.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class WeatherResult {
    @Id
    @GeneratedValue
    private int weatherResultId;
    private String city;
    private int weatherMonth;
    private int weatherYear;
    private int midTemperature;
    private String risk;
    @Transient
    private List<Weather> weathers;
    @Transient
    private List<WeatherRisk> weatherRisks;

    public WeatherResult() {}

    // Constructor que toma las listas de Weather y WeatherRisk
    public WeatherResult(List<Weather> weathers, List<WeatherRisk> weatherRisks) {
        this.weathers = weathers;
        this.weatherRisks = weatherRisks;
        this.calculateWeatherResults();
    }
    public void calculateWeatherResults() {
        if (!weatherRisks.isEmpty()) {
            this.risk = findMostRepeatedRisk(weatherRisks);;
        }
        if (!weathers.isEmpty()) {
            Weather firstWeather = weathers.get(0);
            Calendar cal = Calendar.getInstance();
            cal.setTime(firstWeather.getDate());
            this.city = firstWeather.getCity();
            this.weatherMonth = cal.get(Calendar.MONTH) + 1;
            this.weatherYear = cal.get(Calendar.YEAR);
            double averageTemperature = weathers.stream()
                    .mapToInt(Weather::getTemperature)
                    .average()
                    .orElse(0.0);
            this.midTemperature = (int) averageTemperature;
        }
    }

    private String findMostRepeatedRisk(List<WeatherRisk> risks) {
        int mostRepeatedCount = 0;
        String mostRepeatedRisk = null;
        Map<String, Integer> riskFrequencyMap = new HashMap<>();

        for (WeatherRisk weatherRisk : weatherRisks) {
            Risk risk = weatherRisk.getRisk();
            int frequency = riskFrequencyMap.getOrDefault(risk, 0) + 1;
            riskFrequencyMap.put(risk.name(), frequency);

            if (frequency > mostRepeatedCount) {
                mostRepeatedCount = frequency;
                mostRepeatedRisk = risk.name();
            }
        }

        return mostRepeatedRisk;
    }

    private String getCityFromKey(String key) {
        return key.split("-")[0];
    }

    private int getMonthFromKey(String key) {
        return Integer.parseInt(key.split("-")[1]);
    }

    private int getYearFromKey(String key) {
        return Integer.parseInt(key.split("-")[2]);
    }

    public int getWeatherResultId() {
        return weatherResultId;
    }

    public void setWeatherResultId(int weatherResultId) {
        this.weatherResultId = weatherResultId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getWeatherMonth() {
        return weatherMonth;
    }

    public void setWeatherMonth(int weatherMonth) {
        this.weatherMonth = weatherMonth;
    }

    public int getWeatherYear() {
        return weatherYear;
    }

    public void setWeatherYear(int weatherYear) {
        this.weatherYear = weatherYear;
    }

    public int getMidTemperature() {
        return midTemperature;
    }

    public void setMidTemperature(int midTemperature) {
        this.midTemperature = midTemperature;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public List<WeatherRisk> getWeatherRisks() {
        return weatherRisks;
    }

    public void setWeatherRisks(List<WeatherRisk> weatherRisks) {
        this.weatherRisks = weatherRisks;
    }
}
