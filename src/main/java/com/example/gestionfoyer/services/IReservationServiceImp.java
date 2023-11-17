package com.example.gestionfoyer.services;

import com.example.gestionfoyer.entities.Chambre;
import com.example.gestionfoyer.entities.Etudiant;
import com.example.gestionfoyer.entities.Reservation;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.ChambreRepository;
import com.example.gestionfoyer.repositories.EtudiantRepository;
import com.example.gestionfoyer.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IReservationServiceImp implements IReservationService{
    private final ReservationRepository reservationRepository;
    private final EtudiantRepository etudiantRepository;
    private final ChambreRepository chambreRepository;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return (List<Reservation>) this.reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return this.reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return this.reservationRepository.findById(idReservation).orElseThrow(() -> new ResourceNotFoundException("reservation","id", idReservation));
    }

    @Override
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
        Optional<Chambre> chambreOptional = chambreRepository.findById(idChambre);
        if(chambreOptional.isPresent())
        {
            Chambre chambre = chambreOptional.get();
            String idReservation = chambre.getNumeroChambre().toString()
                    +chambre.getBloc().getNomBloc()+Integer.toString(LocalDate.now().getYear());
            Optional<Reservation> reservationOptional = reservationRepository.findById(idReservation);
            if(reservationOptional.isPresent())
            {
                Reservation reservation = reservationOptional.get();
                if(reservation.isEstValide())
                {
                    if(chambre.getTypeC().toString().equals("DOUBLE"))
                    {
                        reservation.setEstValide(false);
                    }else{
                        if(reservation.getEtudiants().size()==2)
                        {
                            reservation.setEstValide(false);
                        }
                    }
                    Set<Etudiant> etudiantList = reservation.getEtudiants();
                    etudiantList.add(etudiant);
                    reservation.setEtudiants(etudiantList);
                  return reservationRepository.save(reservation);
                }else{

                    throw  new ResourceNotFoundException("Reservation n'est pas valide ou","id", idReservation );
                }
            }else{
                Reservation nouvelleReservation = new Reservation(idReservation,LocalDate.now(),true,Set.of(etudiant));
                if(chambre.getTypeC().toString().equals("SIMPLE"))
                {
                    nouvelleReservation.setEstValide(false);
                }
                return reservationRepository.save(nouvelleReservation);
            }
        }
        else
        {
           throw  new ResourceNotFoundException("Chambre","id", Long.toString(idChambre));
        }
    }
}
