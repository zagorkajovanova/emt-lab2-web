package mk.ukim.finki.emt.lab2application.service.impl;

import mk.ukim.finki.emt.lab2application.model.Author;
import mk.ukim.finki.emt.lab2application.model.Book;
import mk.ukim.finki.emt.lab2application.model.enums.Category;
import mk.ukim.finki.emt.lab2application.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab2application.model.exceptions.BookIdNotFoundException;
import mk.ukim.finki.emt.lab2application.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab2application.repository.AuthorRepository;
import mk.ukim.finki.emt.lab2application.repository.BookRepository;
import mk.ukim.finki.emt.lab2application.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> addBook(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> editBook(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookIdNotFoundException(id));
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookIdNotFoundException(id));
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(String name) {
        Book book = this.bookRepository.findByName(name).orElseThrow(BookNotFoundException::new);
        Integer copies = book.getAvailableCopies()-1;
        book.setAvailableCopies(copies);

        return Optional.of(this.bookRepository.save(book));
    }
}
