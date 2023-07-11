package com.bosonit.formacion.block11uploaddownloadfiles.repository;

import com.bosonit.formacion.block11uploaddownloadfiles.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FicheroRepository extends JpaRepository<Fichero, Integer> {
    Optional<Fichero> findByFileName (String fileName);
}
