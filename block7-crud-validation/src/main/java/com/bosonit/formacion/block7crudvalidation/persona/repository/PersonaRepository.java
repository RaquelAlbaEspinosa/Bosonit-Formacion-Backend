package com.bosonit.formacion.block7crudvalidation.persona.repository;

import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
