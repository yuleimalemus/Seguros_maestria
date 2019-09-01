package com.seguros.seguros.repositories;

import com.seguros.seguros.models.Corredor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CorredorRepository extends CrudRepository<Corredor, Long> {
    Optional<Corredor> findByCcCorredorAndContraseña(String ccCorredor, String contraseña);
}

