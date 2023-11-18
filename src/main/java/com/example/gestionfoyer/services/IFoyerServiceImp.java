package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.entities.Foyer;
import com.example.gestionfoyer.entities.Universite;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.BlocRepository;
import com.example.gestionfoyer.repositories.FoyerRepository;
import com.example.gestionfoyer.repositories.UniversiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class IFoyerServiceImp implements IFoyerService{
    private final FoyerRepository foyerRepository;
    private final UniversiteRepository universiteRepository;
    private final BlocRepository blocRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return (List<Foyer>) foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
      return foyerRepository.findById(idFoyer).orElseThrow(() -> new ResourceNotFoundException("foyer","id", Long.toString(idFoyer)));
    }

    @Override
    public void removeFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = this.universiteRepository.findById(idUniversite).orElseThrow(() ->new ResourceNotFoundException("universite","id",Long.toString(idUniversite)));
        foyer.setUniversite(universite);
        Foyer savedFoyer = foyerRepository.save(foyer);

        Set<Bloc> blocs = new HashSet<>();
        for (Bloc bloc : foyer.getBlocs()) {
            bloc.setFoyer(savedFoyer);
            blocs.add(blocRepository.save(bloc));
        }
        savedFoyer.setBlocs(blocs);
        return savedFoyer;
    }
}
