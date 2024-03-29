package io.github.hotelmanagement.model.date;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SystemDateTimeProvider implements DateTimeProvider{

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
