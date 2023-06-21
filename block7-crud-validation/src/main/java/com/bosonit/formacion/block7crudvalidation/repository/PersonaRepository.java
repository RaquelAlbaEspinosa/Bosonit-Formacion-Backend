package com.bosonit.formacion.block7crudvalidation.repository;

import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
