package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book create(String title,String genre, Double rating, Long authorId) {
        Author author = authorRepository.findById(authorId);
        Book book = new Book(title, genre, rating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, Double rating, Long authorId) {
        Author author = authorRepository.findById(authorId);
        Book book = bookRepository.findById(id);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);
        book.setAverageRating(rating);
        bookRepository.save(book);
        return null;
    }
}
