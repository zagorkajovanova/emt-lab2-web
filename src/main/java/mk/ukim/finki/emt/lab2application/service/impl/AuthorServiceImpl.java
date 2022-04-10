package mk.ukim.finki.emt.lab2application.service.impl;

import mk.ukim.finki.emt.lab2application.model.Author;
import mk.ukim.finki.emt.lab2application.model.Country;
import mk.ukim.finki.emt.lab2application.repository.AuthorRepository;
import mk.ukim.finki.emt.lab2application.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByNameAndSurname(String name, String surname) {
        return this.authorRepository.findByNameAndSurname(name,surname);
    }

    @Override
    public Author create(String name, String surname, Country country) {
        return this.authorRepository.save(new Author(name,surname,country));
    }
}
