package com.example.gestionfoyer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    private String idReservation;
    private LocalDate anneeUniversitaire;
    private boolean estValide;
    @ManyToMany(mappedBy = "reservations")
    private Set<Etudiant> etudiants;

}
