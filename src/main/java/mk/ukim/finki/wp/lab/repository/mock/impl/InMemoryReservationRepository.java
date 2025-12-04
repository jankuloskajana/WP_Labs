package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryReservationRepository implements mk.ukim.finki.wp.lab.repository.mock.InMemoryReservationRepository {
    @Override
    public Reservation save(Reservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
