package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
}
