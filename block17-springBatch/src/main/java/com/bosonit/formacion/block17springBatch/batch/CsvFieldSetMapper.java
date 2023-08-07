package com.bosonit.formacion.block17springBatch.batch;

import com.bosonit.formacion.block17springBatch.model.Weather;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class CsvFieldSetMapper implements FieldSetMapper<Weather> {

    @Override
    public Weather mapFieldSet(FieldSet fieldSet) throws BindException {
        Weather weather = new Weather();
        weather.setCity(fieldSet.readString("City"));
        weather.setDate(parseDate(fieldSet.readString("Date")));
        weather.setTemperature(fieldSet.readInt("Temperature"));
        System.out.println("este es mi mapFieldSet: " + weather);
        return weather;
    }
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }
}

