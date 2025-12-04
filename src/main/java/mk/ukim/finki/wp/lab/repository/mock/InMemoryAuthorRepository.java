package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;

public interface InMemoryAuthorRepository {
    List<Author> findAll();
    Author findById(Long id);
    void delete(Long id);
    Author save(Author author);
}
