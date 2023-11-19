package com.example.gestionfoyer.repositories;

import com.example.gestionfoyer.entities.Bloc;
import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.enums.TypeChambre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChambreRepository extends CrudRepository<Chambre,Long> {
    @Query("SELECT c FROM Chambre c WHERE c.numeroChambre IN :numChambre")
    public List<Chambre> findChambresByNumChambre(List<Long> numChambre);

    @Query(value="SELECT * FROM chambre WHERE id_chambre IN (SELECT chambre_id_chambre FROM chambre_reservations WHERE reservations_id_reservation  = :idReservation)",nativeQuery = true)
    public Chambre findChambreByIdReservation(@Param("idReservation") String idReservation);

    @Query(value = "select * from chambre c where c.id_bloc in ( select id_bloc from bloc b where b.id_foyer in (select id_foyer from universite u where u.nom_universite = :nom ))",nativeQuery = true)
    public List<Chambre> getChambresParNomUniversite(@Param("nom") String nomUniversite);

   @Query(value ="select * from chambre c where c.typec = :typec and c.id_bloc = :idBloc",nativeQuery = true)
    public List<Chambre> getChambresParBlocEtType (@Param("idBloc") long idBloc,@Param("typec") TypeChambre typeC);

    List<Chambre> findChambresByTypeCAndBloc(TypeChambre typeC, Bloc bloc);
}

