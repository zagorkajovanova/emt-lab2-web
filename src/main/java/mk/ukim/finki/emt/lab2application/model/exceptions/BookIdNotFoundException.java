package mk.ukim.finki.emt.lab2application.model.exceptions;

public class BookIdNotFoundException extends RuntimeException{
    public BookIdNotFoundException(Long id){
        super("Book with id: "+id+" not found!");
    }
}
