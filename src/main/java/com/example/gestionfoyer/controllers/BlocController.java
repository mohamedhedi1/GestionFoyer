package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.services.IBlocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blocs")
@RequiredArgsConstructor
@Slf4j
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

    @PostMapping("/affecterChambresABloc/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre,@PathVariable("idBloc") long idBloc)
    {
        return this.blocService.affecterChambresABloc(numChambre,idBloc);
    }

    @GetMapping("/date/{d}")
    public void sendDate(@PathVariable("d") LocalDate d) //@DateTimeFormat(pattern = "yyyy-MM-dd")
    {

        log.info(String.valueOf(d));
    }

}
