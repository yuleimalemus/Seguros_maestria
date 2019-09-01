package com.seguros.seguros.repositories;

import com.seguros.seguros.models.Poliza;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PolizaRepository extends CrudRepository<Poliza, Long> {
    Optional<Poliza> findByNumeroPoliza(String numeroPoliza);
    Iterable<Poliza> findDistinctByCodigoCorredor(String codCorredor);
    Iterable<Poliza> findAllByCcCliente(Integer cc);
    Optional<Poliza> findByPlacaAndTipo(String placa, String tipo);
}

