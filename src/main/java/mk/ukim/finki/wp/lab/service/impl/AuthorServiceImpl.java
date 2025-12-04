package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author not found"));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author create(String name, String surname, String country, String biography) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || country == null || country.isEmpty() || biography == null || biography.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Author author = new Author(name, surname, country, biography);
        authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(Long id, String name, String surname, String country, String biography) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() || country == null || country.isEmpty() || biography == null || biography.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author not found"));
        author.setBiography(biography);
        author.setCountry(country);
        author.setName(name);
        author.setSurname(surname);
        authorRepository.save(author);
        return author;
    }
}
