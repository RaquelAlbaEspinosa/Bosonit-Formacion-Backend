package com.bosonit.formacion.block17springBatch.batch;

import com.bosonit.formacion.block17springBatch.error.ErrorRecord;
import com.bosonit.formacion.block17springBatch.model.Weather;
import org.springframework.batch.item.ItemProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WeatherProcessor implements ItemProcessor<Weather, Weather> {
    private int errorCount = 0;

    @Override
    public Weather process(Weather item) throws Exception {
        int temperature = item.getTemperature();
        File file = new File("REGISTROS_ERRONEOS.CSV");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        if (temperature < -20 || temperature > 50) {
            item.setValid(false);
            errorCount++;
            ErrorRecord errorRecord = new ErrorRecord(item.getCity(), item.getDate(), item.getTemperature());
            try {
                bufferedWriter.write(errorRecord.toString());
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                throw  new IOException();
            }
            return null;
        } else {
            System.out.println("este es mi weatherProcessor: " + item);
            return item;
        }
    }

    public int getErrorCount() {
        return errorCount;
    }
}
