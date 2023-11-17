package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.BlocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IBlocServiceImp implements IBlocService{
    private final BlocRepository blocRepository;
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
}
