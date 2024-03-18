package io.github.hotelmanagement.model.room.exception;

public class GetAvailableRoomException extends RuntimeException{

    private static final String MESSAGE = "An error occurred while retrieving the available room";
    public GetAvailableRoomException(Throwable cause){
        super(MESSAGE,cause);
    }
}
