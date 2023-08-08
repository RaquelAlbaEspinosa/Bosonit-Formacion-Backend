package com.bosonit.formacion.block17springBatch.batch;

import com.bosonit.formacion.block17springBatch.model.Weather;
import com.bosonit.formacion.block17springBatch.model.WeatherResult;
import com.bosonit.formacion.block17springBatch.model.WeatherRisk;
import com.bosonit.formacion.block17springBatch.repository.WeatherRepository;
import com.bosonit.formacion.block17springBatch.repository.WeatherResultRepository;
import com.bosonit.formacion.block17springBatch.repository.WeatherRiskRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class WeatherWriter implements ItemWriter<Weather> {
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    WeatherRiskRepository weatherRiskRepository;
    @Autowired
    WeatherResultRepository weatherResultRepository;
    private List<Weather> createdWeathers = new ArrayList<>();
    private List<WeatherRisk> createdWeatherRisks = new ArrayList<>();
    private List<WeatherResult> createdWeatherResult = new ArrayList<>();
    @Override
    public void write(Chunk<? extends Weather> chunk) throws Exception {
        Map<String, WeatherResult> weatherResultMap = new HashMap<>();

        for (Weather weather : chunk) {
            WeatherRisk weatherRisk = new WeatherRisk(weather);
            weather.setWeatherRisk(weatherRisk);

            createdWeathers.add(weather);
            createdWeatherRisks.add(weatherRisk);

            Calendar cal = Calendar.getInstance();
            cal.setTime(weather.getDate());
            String key = weather.getCity() + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR);
            WeatherResult weatherResult = weatherResultMap.get(key);

            if (weatherResult == null) {
                // Crear un nuevo WeatherResult si no existe para la ciudad, año y mes actual
                weatherResult = new WeatherResult(new ArrayList<>(), new ArrayList<>());
                weatherResult.setCity(weather.getCity());
                weatherResult.setWeatherMonth(cal.get(Calendar.MONTH) + 1);
                weatherResult.setWeatherYear(cal.get(Calendar.YEAR));
                weatherResultMap.put(key, weatherResult);
            }

            // Agregar Weather y WeatherRisk a las listas en WeatherResult
            weatherResult.getWeathers().add(weather);
            weatherResult.getWeatherRisks().add(weatherRisk);

            weatherRepository.save(weather);
            weatherRiskRepository.save(weatherRisk);
        }

        // Calcular los resultados y guardar los WeatherResult después de procesar todos los elementos
        for (WeatherResult weatherResult : weatherResultMap.values()) {
            createdWeatherResult.add(weatherResult);
            weatherResult.calculateWeatherResults();
            weatherResultRepository.save(weatherResult);
        }
        System.out.println("este es mi itemWriter");
    }
    public List<Weather> getCreatedWeathers() {
        return createdWeathers;
    }

    public List<WeatherRisk> getCreatedWeatherRisks() {
        return createdWeatherRisks;
    }
    public List<WeatherResult> getCreatedWeatherResult(){
        return createdWeatherResult;
    }
}
