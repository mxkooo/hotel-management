package io.github.hotelmanagement.model.date;

import java.time.LocalDateTime;

public interface DateTimeProvider {
    LocalDateTime now();
}
