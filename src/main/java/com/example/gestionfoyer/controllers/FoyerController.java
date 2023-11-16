package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Foyer;
import com.example.gestionfoyer.exceptions.GlobalExceptionHandler;
import com.example.gestionfoyer.exceptions.MyResourceNotFoundException;
import com.example.gestionfoyer.services.IFoyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foyers")
@RequiredArgsConstructor
public class FoyerController {
    private final IFoyerService foyerService;

    @GetMapping
    public List<Foyer> retrieveAllFoyers()
    {
        return this.foyerService.retrieveAllFoyers();
    }

    @PostMapping
    public Foyer addFoyer(@RequestBody Foyer f)
    {
        return this.foyerService.addFoyer(f);
    }

    @PutMapping
    public Foyer updateFoyer (@RequestBody  Foyer f)
    {
        return this.foyerService.updateFoyer(f);
    }

    @GetMapping("/{id}")
    public Foyer retrieveFoyer (@PathVariable("id") long idFoyer)
    {
        return this.foyerService.retrieveFoyer(idFoyer);
    }

    @DeleteMapping("/{id}")
    public void removeFoyer (@PathVariable("id")  long idFoyer)
    {
         this.foyerService.removeFoyer(idFoyer);
    }
}
