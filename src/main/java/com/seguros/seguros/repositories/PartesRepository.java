package com.seguros.seguros.repositories;

import com.seguros.seguros.models.ParteAsegurada;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface PartesRepository extends CrudRepository<ParteAsegurada, String> {
    @Override
    @Transactional
    <S extends ParteAsegurada> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @Transactional
    <S extends ParteAsegurada> S save(S s);
}

