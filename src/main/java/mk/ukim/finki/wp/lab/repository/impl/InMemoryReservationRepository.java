package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Reservation;
import mk.ukim.finki.wp.lab.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryReservationRepository implements ReservationRepository {
    @Override
    public Reservation save(Reservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
