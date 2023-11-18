package com.example.gestionfoyer.repositories;

import com.example.gestionfoyer.entities.Chambre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChambreRepository extends CrudRepository<Chambre,Long> {
    @Query("SELECT c FROM Chambre c WHERE c.numeroChambre IN :numChambre")
    public List<Chambre> findChambresByNumChambre(List<Long> numChambre);

    @Query(value="SELECT * FROM chambre WHERE id_chambre IN (SELECT chambre_id_chambre FROM chambre_reservations WHERE reservations_id_reservation  = :idReservation)",nativeQuery = true)
    public Chambre findChambreByIdReservation(@Param("idReservation") String idReservation);
}

