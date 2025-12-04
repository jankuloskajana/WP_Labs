package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryAuthorRepository implements mk.ukim.finki.wp.lab.repository.mock.InMemoryAuthorRepository {
    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Author findById(Long id) {
        return DataHolder.authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        DataHolder.authors.removeIf(m -> m.getId().equals(id));
    }


    @Override
    public Author save(Author author) {
        delete(author.getId());
        DataHolder.authors.add(author);
        return author;
    }


}
