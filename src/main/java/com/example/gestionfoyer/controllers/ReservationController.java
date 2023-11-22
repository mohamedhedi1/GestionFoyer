package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.entities.Reservation;
import com.example.gestionfoyer.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;

    @GetMapping
    public List<Reservation> retrieveAllReservation()
    {
        return this.reservationService.retrieveAllReservation();
    }

    @PutMapping
    public Reservation updateReservation(@RequestBody  Reservation res)
    {
        return this.reservationService.updateReservation(res);
    }

    @GetMapping("/{id}")
    public Reservation retrieveReservation (@PathVariable("id") String idReservation)
    {
        return this.reservationService.retrieveReservation(idReservation);
    }
    @PostMapping("/{idChambre}/{cinEtudiant}")
    public Reservation ajouterReservation(@PathVariable("idChambre") long idChambre,@PathVariable("cinEtudiant") long cinEtudiant)
    {
        return this.reservationService.ajouterReservation(idChambre,cinEtudiant);
    }

    @PutMapping("/annulerReservation/{cinEtudiant}")
    public Reservation annulerReservation(@PathVariable("cinEtudiant") long cinEtudiant)
    {
        return this.reservationService.annulerReservation(cinEtudiant);
    }

    @GetMapping("/getReservationParAnneeUniversitaireEtNomUniversite/{anneeUniversite}/{nomUniversite}")
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@PathVariable("anneeUniversite") LocalDate anneeUniversite,@PathVariable("nomUniversite") String nomUniversite)
    {
        return this.reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversite,nomUniversite);
    }



}
