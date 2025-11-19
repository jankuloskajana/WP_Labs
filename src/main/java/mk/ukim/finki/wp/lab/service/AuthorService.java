package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    void delete(Long id);
    Author create(String name, String surname, String country, String biography);
    Author update(Long id, String name, String surname, String country, String biography);

}
