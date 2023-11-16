package com.example.gestionfoyer.controllers;

import com.example.gestionfoyer.entities.Reservation;
import com.example.gestionfoyer.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
