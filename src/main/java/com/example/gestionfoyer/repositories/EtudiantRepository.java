package com.example.gestionfoyer.repositories;

import com.example.gestionfoyer.entities.Etudiant;
import org.springframework.data.repository.CrudRepository;

public interface EtudiantRepository extends CrudRepository<Etudiant,Long> {
}
