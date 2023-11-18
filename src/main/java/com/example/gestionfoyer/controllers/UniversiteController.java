package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Universite;
import com.example.gestionfoyer.services.IUniversiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/universites")
public class UniversiteController {
    private final IUniversiteService universiteService;

    @GetMapping
    public List<Universite> retrieveAllUniversities()
    {
        return this.universiteService.retrieveAllUniversities();
    }

    @PostMapping
    public Universite addUniversite (@RequestBody  Universite u)
    {
        return this.universiteService.addUniversite(u);
    }

    @PutMapping
    public Universite updateUniversite (@RequestBody Universite u)
    {
        return this.universiteService.updateUniversite(u);
    }


    @GetMapping("/{id}")
    public Universite retrieveUniversite (@PathVariable("id") long idUniversite)
    {
        return this.universiteService.retrieveUniversite(idUniversite);
    }

    @PostMapping("/affecterFoyerAUniversite/{nomUniversite}/{idFoyer}")
    public Universite affecterFoyerAUniversite(@PathVariable("idFoyer") long idFoyer,@PathVariable("nomUniversite") String nomUniversite) {
        return this.universiteService.affecterFoyerAUniversite(idFoyer,nomUniversite);
    }

    @PutMapping("/desaffecterFoyerAUniversite/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable("idUniversite") long idUniversite)
    {
        return this.universiteService.desaffecterFoyerAUniversite(idUniversite);
    }

}
