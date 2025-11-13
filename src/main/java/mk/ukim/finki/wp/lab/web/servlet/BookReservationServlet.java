package mk.ukim.finki.wp.lab.web.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Reservation;
import mk.ukim.finki.wp.lab.service.ReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ReservationService reservationService;

    public BookReservationServlet(SpringTemplateEngine templateEngine, ReservationService reservationService) {
        this.templateEngine = templateEngine;
        this.reservationService = reservationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numberOfCopies = Integer.parseInt(req.getParameter("numberOfCopies"));


        String clientIp = req.getRemoteAddr();


        context.setVariable("clientIp", clientIp);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("readerName", readerName);
        context.setVariable("readerAddress", readerAddress);
        context.setVariable("numberOfCopies", numberOfCopies);

        templateEngine.process("reservationConfirmation.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        Long numberOfCopies = Long.parseLong(req.getParameter("numberOfCopies"));

        Reservation reservation = reservationService.placeReservation(bookTitle, readerName,readerAddress,numberOfCopies);

        String clientIp = req.getRemoteAddr();

        context.setVariable("clientIp", clientIp);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("readerName", readerName);
        context.setVariable("readerAddress", readerAddress);
        context.setVariable("numberOfCopies", numberOfCopies);

        templateEngine.process("reservationConfirmation.html", context, resp.getWriter());

    }
}