package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Foyer;
import com.example.gestionfoyer.exceptions.GlobalExceptionHandler;
import com.example.gestionfoyer.exceptions.MyResourceNotFoundException;
import com.example.gestionfoyer.repositories.FoyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class IFoyerServiceImp implements IFoyerService{
    private final FoyerRepository foyerRepository;
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
    public Foyer retrieveFoyer(long idFoyer) throws MyResourceNotFoundException {
      return foyerRepository.findById(idFoyer).orElseThrow(() -> new MyResourceNotFoundException("Foyer avec l'id"+idFoyer+" n'existe pas!"));
    }

    @Override
    public void removeFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}
