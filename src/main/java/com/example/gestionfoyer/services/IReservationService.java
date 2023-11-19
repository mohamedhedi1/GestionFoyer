package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservation();

    Reservation updateReservation (Reservation res);

    Reservation retrieveReservation (String idReservation);

    public Reservation ajouterReservation (long idChambre, long cinEtudiant) ;

    public Reservation annulerReservation (long cinEtudiant);

    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(LocalDate anneeUniversite, String nomUniversite);
}
