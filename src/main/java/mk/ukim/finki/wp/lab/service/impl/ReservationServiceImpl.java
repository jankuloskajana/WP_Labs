package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Reservation;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryReservationRepository;
import mk.ukim.finki.wp.lab.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final InMemoryReservationRepository reservationRepository;

    public ReservationServiceImpl(InMemoryReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation placeReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        Reservation reservation = new Reservation(bookTitle, readerName, readerAddress, numberOfCopies);
        return reservationRepository.save(reservation);
    }
}
