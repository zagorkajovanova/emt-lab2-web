package mk.ukim.finki.emt.lab2application.service;

import mk.ukim.finki.emt.lab2application.model.Author;
import mk.ukim.finki.emt.lab2application.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> findByNameAndSurname(String name, String surname);
    Author create(String name, String surname, Country country);
}
