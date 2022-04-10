package mk.ukim.finki.emt.lab2application.model.exceptions;

import mk.ukim.finki.emt.lab2application.model.Author;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id){
        super(String.format("The author with id %d, was not found", id));
    }
}
