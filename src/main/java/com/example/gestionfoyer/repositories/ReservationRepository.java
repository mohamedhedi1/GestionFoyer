package com.example.gestionfoyer.repositories;

import com.example.gestionfoyer.entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,String> {
    @Query(value = "SELECT * " +
            "FROM reservation r " +
            "WHERE r.annee_universitaire = :annee " +
            "  AND r.id_reservation IN ( " +
            "    SELECT id_reservation " +
            "    FROM chambre_reservations cr " +
            "    WHERE cr.reservations_id_reservation = r.id_reservation " +
            "      AND cr.chambre_id_chambre IN ( " +
            "        SELECT c.id_chambre " +
            "        FROM chambre c " +
            "        WHERE c.id_bloc IN ( " +
            "          SELECT b.id_bloc " +
            "          FROM bloc b " +
            "          WHERE b.id_foyer IN ( " +
            "            SELECT u.id_foyer " +
            "            FROM universite u " +
            "            WHERE u.nom_universite = :nom " +
            "          ) " +
            "        ) " +
            "      ) " +
            "  )",nativeQuery = true)
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@Param("annee") LocalDate anneeUniversite,@Param("nom") String nomUniversite);

}
