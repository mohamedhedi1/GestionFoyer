package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.enums.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre (Chambre c);

    Chambre retrieveChambre (long idChambre);

    List<Chambre> getChambresParNomUniversite(String nomUniversite);

    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);


}
