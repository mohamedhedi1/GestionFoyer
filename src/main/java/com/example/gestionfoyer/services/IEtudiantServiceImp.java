package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Etudiant;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IEtudiantServiceImp implements IEtudiantService {
    private final EtudiantRepository etudiantRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return (List<Etudiant>) this.etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        return (List<Etudiant>) this.etudiantRepository.saveAll(etudiants);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return this.etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant)  {
        return this.etudiantRepository.findById(idEtudiant).orElseThrow(() -> new ResourceNotFoundException("etudiant","id", Long.toString(idEtudiant)));
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        this.etudiantRepository.deleteById(idEtudiant);

    }
}
