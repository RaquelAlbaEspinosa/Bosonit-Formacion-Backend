package com.bosonit.formacion.block7crud.repository;

import com.bosonit.formacion.block7crud.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
