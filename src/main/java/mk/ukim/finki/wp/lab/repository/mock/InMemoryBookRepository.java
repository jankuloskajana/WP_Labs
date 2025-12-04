package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface InMemoryBookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book findById(Long id);
    void delete(Long id);
    Book save(Book book);
}
