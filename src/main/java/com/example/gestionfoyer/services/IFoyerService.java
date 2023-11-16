package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Foyer;
import com.example.gestionfoyer.exceptions.GlobalExceptionHandler;
import com.example.gestionfoyer.exceptions.MyResourceNotFoundException;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer (Foyer f);

    Foyer updateFoyer (Foyer f);

    Foyer retrieveFoyer (long idFoyer) throws MyResourceNotFoundException;

    void removeFoyer (long idFoyer);
}
