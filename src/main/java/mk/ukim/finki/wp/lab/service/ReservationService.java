package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Reservation;

public interface ReservationService {
    Reservation placeReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies);

}
