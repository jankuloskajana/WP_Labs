package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init(){
        books.add(new Book("Crime and Punishment", "Classics", 4.9));
        books.add(new Book("Wuthering Heights", "Classics", 5.0));
        books.add(new Book("The Trial", "Classics", 4.3));
        books.add(new Book("The Myth Of Sisyphus", "Philosophy", 4.0));
        books.add(new Book("Junk", "Fiction", 3.8));
        books.add(new Book("Gideon the Ninth", "Fantasy", 5.0));
        books.add(new Book("The Poppy War", "Fantasy", 4.5));
        books.add(new Book("Crush", "Poetry", 4.8));
        books.add(new Book("Ariel", "Poetry", 4.9));
        books.add(new Book("Eileen", "Fiction", 4.2));
    }
}