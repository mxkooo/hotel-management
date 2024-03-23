package io.github.hotelmanagement.model.exception;

public class NotFoundException extends RuntimeException{
   public NotFoundException (String message){
       super(message);
   }

    public NotFoundException(final Long id) {
        super(String.format("No entity with id: %s", id));
    }

    public NotFoundException(final String message, final Object args) {
        super(String.format(message, args));
    }
}
