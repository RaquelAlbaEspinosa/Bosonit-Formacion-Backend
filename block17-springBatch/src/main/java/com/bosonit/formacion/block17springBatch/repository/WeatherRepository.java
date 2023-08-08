package com.bosonit.formacion.block17springBatch.repository;

import com.bosonit.formacion.block17springBatch.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}
