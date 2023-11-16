package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.services.IBlocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocs")
@RequiredArgsConstructor
public class BlocController {
    private final IBlocService blocService;

    @GetMapping
    public List<Bloc> retrieveBlocs()
    {
        return  this.blocService.retrieveBlocs();
    }

    @PutMapping
    public Bloc updateBloc (@RequestBody  Bloc bloc)
    {
        return this.blocService.updateBloc(bloc);
    }

    @PostMapping
    public Bloc addBloc (@RequestBody  Bloc bloc)
    {
        return this.blocService.addBloc(bloc);
    }


    @GetMapping("/{id}")
    public Bloc retrieveBloc (@PathVariable("id")  long idBloc)
    {
        return this.blocService.retrieveBloc(idBloc);
    }

    @DeleteMapping("/{id}")
    public void removeBloc (@PathVariable("id") long idBloc)
    {
        this.blocService.removeBloc(idBloc);
    }

}
