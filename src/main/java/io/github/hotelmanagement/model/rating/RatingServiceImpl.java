package io.github.hotelmanagement.model.rating;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RatingServiceImpl {

//    public RatingRoomDTO rateRoom(){
        /*
        |na razie funkcja ocenia ogólnie, a nie konkretny pokój|
        1. co ma funkcja przyjmować?
        - user id [sprawdza rezerwacje]
        2. jak przekazywac komentarz i ocene?
        -
        3. jak wiedziec czy user juz byl?
        - if user[id] getReservations != null
        4. po wystawieniu oceny zmien didUserRate na true
        5. jak wiedziec czy user juz ocenil?
        - if didUserRate = true wtedy throw Exception("Już dokonałeś oceny")
        * */


//    }
}
