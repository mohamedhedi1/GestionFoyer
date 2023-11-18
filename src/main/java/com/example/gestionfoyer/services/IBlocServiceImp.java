package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.BlocRepository;
import com.example.gestionfoyer.repositories.ChambreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IBlocServiceImp implements IBlocService{
    private final BlocRepository blocRepository;
    private final ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveBlocs() {
        return (List<Bloc>) this.blocRepository.findAll();
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return this.blocRepository.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return this.blocRepository.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return this.blocRepository.findById(idBloc).orElseThrow(() -> new ResourceNotFoundException("bloc","id", Long.toString(idBloc)));
    }

    @Override
    public void removeBloc(long idBloc) {
        this.blocRepository.deleteById(idBloc);

    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Optional<Bloc> blocOptional = this.blocRepository.findById(idBloc);
        if(blocOptional.isPresent())
        {
            Set<Chambre> chambres= new HashSet<>(this.chambreRepository.findChambresByNumChambre(numChambre));
            Bloc bloc = blocOptional.get();
            bloc.setChambres(chambres);
            return this.blocRepository.save(bloc);
        }
        else {
            throw new ResourceNotFoundException("bloc","id",Long.toString(idBloc));
        }
    }
}
