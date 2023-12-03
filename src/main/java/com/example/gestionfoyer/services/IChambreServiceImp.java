package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.enums.TypeChambre;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.BlocRepository;
import com.example.gestionfoyer.repositories.ChambreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IChambreServiceImp implements IChambreService{
    private final ChambreRepository chambreRepository;
    private final BlocRepository blocRepository;
    @Override
    public List<Chambre> retrieveAllChambres() {
        return (List<Chambre>) chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        Bloc b = this.blocRepository.findById(c.getBloc().getIdBloc()).orElse(null);
        System.out.println(b);
        c.setBloc(b);
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElseThrow(() -> new ResourceNotFoundException("chambre","id", Long.toString(idChambre)));
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.getChambresParNomUniversite(nomUniversite);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return this.chambreRepository.getChambresParBlocEtType(idBloc,typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return this.chambreRepository.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite,type);
    }
}
