package com.seguros.seguros.repositories;

import com.seguros.seguros.models.TipoPoliza;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TipoPolizaRepository extends CrudRepository<TipoPoliza, String> {
    List<TipoPoliza> findAllByClase(String id);
}

