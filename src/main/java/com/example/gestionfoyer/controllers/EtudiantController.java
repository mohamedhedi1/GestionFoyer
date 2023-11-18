package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Etudiant;
import com.example.gestionfoyer.services.IEtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
@RequiredArgsConstructor
public class EtudiantController {
    private final IEtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> retrieveAllEtudiants()
    {
        return etudiantService.retrieveAllEtudiants();

    }

    @PostMapping
    public List<Etudiant> addEtudiants(@RequestBody  List<Etudiant> etudiants)
    {
        return etudiantService.addEtudiants(etudiants);
    }

    @PutMapping
    public Etudiant updateEtudiant (@RequestBody  Etudiant e)
    {
        return etudiantService.updateEtudiant(e);
    }

    @GetMapping("/{id}")
    public Etudiant retrieveEtudiant(@PathVariable("id") long idEtudiant)
    {
        return etudiantService.retrieveEtudiant(idEtudiant);
    }

    @DeleteMapping("/{id}")
    public void removeEtudiant(@PathVariable("id") long idEtudiant)
    {
        etudiantService.removeEtudiant(idEtudiant);
    }
}
