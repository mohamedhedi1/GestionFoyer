package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.services.IChambreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chambres")
@RequiredArgsConstructor
@RestController
public class ChambreController {
    private final IChambreService chambreService;

    @GetMapping
    public List<Chambre> retrieveAllChambres()
    {
        return this.chambreService.retrieveAllChambres();
    }

    @PostMapping
    public Chambre addChambre(@RequestBody Chambre c)
    {
        return this.chambreService.addChambre(c);
    }

    @PutMapping
    public Chambre updateChambre(@RequestBody Chambre c)
    {
        return this.chambreService.updateChambre(c);
    }


    @GetMapping("/{id}")
    public Chambre retrieveChambre (@PathVariable("id") long idChambre)
    {
        return this.chambreService.retrieveChambre(idChambre);
    }

}
