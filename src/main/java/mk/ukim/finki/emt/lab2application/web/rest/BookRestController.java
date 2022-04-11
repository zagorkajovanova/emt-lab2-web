package mk.ukim.finki.emt.lab2application.web.rest;

import mk.ukim.finki.emt.lab2application.model.Author;
import mk.ukim.finki.emt.lab2application.model.Book;
import mk.ukim.finki.emt.lab2application.model.dto.BookDto;
import mk.ukim.finki.emt.lab2application.model.enums.Category;
import mk.ukim.finki.emt.lab2application.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab2application.service.AuthorService;
import mk.ukim.finki.emt.lab2application.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "https://emtlibrary.herokuapp.com/")
@RequestMapping("/api")
public class BookRestController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        return this.bookService.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getName))
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }

    @GetMapping("/authors")
    public List<Author> findAllAuthors() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/reserve/{id}")
    public List<Book> markAsTaken(@PathVariable Long id) {
        Optional<Book> book = this.bookService.findById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException();
        }

        this.bookService.markAsTaken(book.get().getName());
        return this.findAll();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.editBook(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return this.bookService.addBook(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthor(), bookDto.getAvailableCopies())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id) {
        this.bookService.deleteBook(id);

        if (this.bookService.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
