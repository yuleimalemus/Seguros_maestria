package com.seguros.seguros.repositories;

import com.seguros.seguros.models.Vehiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPlaca(String id);
    List<Vehiculo> findAllByClienteCC(Integer cc);
    List<Vehiculo> findAllByPlacaIn(Iterable<String> placas);
}

