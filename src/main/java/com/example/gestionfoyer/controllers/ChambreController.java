package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.enums.TypeChambre;
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

    @GetMapping("/getChambresParNomUniversite/{nomUniversite}")
    public List<Chambre> getChambresParNomUniversite(@PathVariable("nomUniversite") String nomUniversite)
    {
        return this.chambreService.getChambresParNomUniversite(nomUniversite);
    }

    @GetMapping("/getChambresParBlocEtType/{idBloc}/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable("idBloc") long idBloc, @PathVariable("typeC") TypeChambre typeC)
    {
        return this.chambreService.getChambresParBlocEtType(idBloc,typeC);
    }

    @GetMapping("/getChambresNonReserveParNomUniversiteEtTypeChambre/{nomUniversite}/{type}")
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@PathVariable("nomUniversite") String nomUniversite,@PathVariable("type") TypeChambre type)
    {
        return this.chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite,type);
    }

}
