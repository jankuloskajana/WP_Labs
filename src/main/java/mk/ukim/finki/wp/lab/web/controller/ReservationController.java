package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Reservation;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bookReservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping()
    public String reserve(@RequestParam String bookTitle,
                          @RequestParam String readerName,
                          @RequestParam String readerAddress,
                          @RequestParam Long numberOfCopies,
                          HttpServletRequest request,
                          Model model, RedirectAttributes redirectAttributes){

        Reservation reservation = reservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);
//          ili ova bez getmap
//        model.addAttribute("readerName", reservation.getReaderName());
//        model.addAttribute("bookTitle", reservation.getBookTitle());
//        model.addAttribute("clientIp", request.getRemoteAddr());
//        model.addAttribute("numberOfCopies", reservation.getNumberOfCopies());
//        return "reservationConfirmation";

        redirectAttributes.addAttribute("readerName", reservation.getReaderName());
        redirectAttributes.addAttribute("bookTitle", reservation.getBookTitle());
        redirectAttributes.addAttribute("clientIp", request.getRemoteAddr());
        redirectAttributes.addAttribute("numberOfCopies", reservation.getNumberOfCopies());

        return "redirect:/bookReservation";

    }


    @GetMapping()
    public String reservationConfirmation(@RequestParam(required = false) String error,
                                          @RequestParam(required = false) String readerName,
                                          @RequestParam(required = false) String bookTitle,
                                          @RequestParam(required = false) String clientIp,
                                          @RequestParam(required = false) String numberOfCopies,
                                          Model model) {
        if(error != null) {
            model.addAttribute("error", error);
        }

        model.addAttribute("readerName", readerName);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("clientIp", clientIp);
        model.addAttribute("numberOfCopies", numberOfCopies);

        return "reservationConfirmation";
    }
}
