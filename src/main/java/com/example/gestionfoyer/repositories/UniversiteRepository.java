package com.example.gestionfoyer.repositories;

import com.example.gestionfoyer.entities.Universite;
import org.springframework.data.repository.CrudRepository;

public interface UniversiteRepository extends CrudRepository<Universite,Long> {
    Universite findByNomUniversite(String nomUniversite);
}
