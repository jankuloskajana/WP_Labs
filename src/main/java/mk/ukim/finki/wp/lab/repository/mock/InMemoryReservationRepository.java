package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Reservation;

public interface InMemoryReservationRepository {
    Reservation save(Reservation reservation);
}
