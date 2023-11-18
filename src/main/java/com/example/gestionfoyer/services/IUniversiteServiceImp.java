package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Foyer;
import com.example.gestionfoyer.entities.Universite;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.FoyerRepository;
import com.example.gestionfoyer.repositories.UniversiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUniversiteServiceImp implements IUniversiteService{
    private final UniversiteRepository universiteRepository;
    private final FoyerRepository foyerRepository;
    @Override
    public List<Universite> retrieveAllUniversities() {
        return (List<Universite>) universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).orElseThrow(() -> new ResourceNotFoundException("universite","id", Long.toString(idUniversite)));
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Universite universite= this.universiteRepository.findByNomUniversite(nomUniversite);
        Optional<Foyer> foyerOptional = this.foyerRepository.findById(idFoyer);
        if(foyerOptional.isPresent())
        {
            universite.setFoyer(foyerOptional.get());
            return this.universiteRepository.save(universite);

        }else{
            throw new ResourceNotFoundException("Foyer","id",Long.toString(idFoyer));
        }

    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Optional<Universite> universiteOptional = this.universiteRepository.findById(idUniversite);
        if(universiteOptional.isPresent())
        {
            Universite universite = universiteOptional.get();
            universite.setFoyer(null);
            return this.universiteRepository.save(universite);
        }
        else {
            throw new ResourceNotFoundException("Universite","id",Long.toString(idUniversite));
        }
    }
}
