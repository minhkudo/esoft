package com.minh.esoft.common.ultils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class DateTimeUltis {

    public LocalDateTime convertInstant2LocalDatetime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public Long convertInstant2LongSecond(Instant instant) {
        if (instant == null) {
            return null;
        }
        return instant.getEpochSecond();
    }
}
