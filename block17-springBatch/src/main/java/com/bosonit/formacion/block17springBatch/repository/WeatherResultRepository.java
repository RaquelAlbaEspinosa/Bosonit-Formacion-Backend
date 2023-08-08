package com.bosonit.formacion.block17springBatch.repository;

import com.bosonit.formacion.block17springBatch.model.WeatherResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherResultRepository extends JpaRepository<WeatherResult, Integer> {
}
