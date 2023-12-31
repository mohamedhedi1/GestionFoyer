package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Etudiant;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();

    List<Etudiant> addEtudiants (List<Etudiant> etudiants);

    Etudiant updateEtudiant (Etudiant e);

    Etudiant retrieveEtudiant(long idEtudiant);

    void removeEtudiant(long idEtudiant);

    Etudiant addEtudiant(Etudiant etudiant) throws MessagingException;
}
