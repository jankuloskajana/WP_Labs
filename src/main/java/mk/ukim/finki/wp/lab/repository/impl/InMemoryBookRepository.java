package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(b ->
                        (text != null && b.getTitle().toLowerCase().contains(text.toLowerCase())) ||
                                (rating != null && b.getAverageRating() >= rating))
                .toList();
    }

    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        DataHolder.books.removeIf(m -> m.getId().equals(id));
    }

    @Override
    public Book save(Book book) {
//        DataHolder.books.removeIf(m -> m.getId().equals(book.getId()));
        delete(book.getId());
        DataHolder.books.add(book);
        return book;
    }
}
