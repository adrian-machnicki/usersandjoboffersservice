package com.machnickiadrian.usersandjoboffersservice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class GenerateUtils {

    public static ZonedDateTime getCreationTime() {
        LocalDate creationDate = LocalDate.of(2018, 12, 26);
        LocalTime creationTime = LocalTime.of(12, 0);
        ZoneId zone = ZoneId.of("Europe/Warsaw");
        return ZonedDateTime.of(creationDate, creationTime, zone);
    }

}
