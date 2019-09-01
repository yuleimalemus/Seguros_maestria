package com.seguros.seguros.repositories;

import com.seguros.seguros.models.Clientes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientesRepository extends CrudRepository<Clientes, Long> {
    Optional<Clientes> findByCc(Long id);
    List<Clientes> findAllByCc(Long cc);
}

