package io.github.hotelmanagement.model.room;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    public static final Logger logger = LoggerFactory.getLogger(RoomService.class);
    private RoomRepository roomRepository;

}
