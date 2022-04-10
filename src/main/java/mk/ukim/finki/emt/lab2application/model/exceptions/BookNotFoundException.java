package mk.ukim.finki.emt.lab2application.model.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super("Book not found!");
    }
}
