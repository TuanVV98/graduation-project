package com.spring.utils;



import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ApiUtil {

    public ApiUtil() {
    }

    public static boolean isEndDateGreaterThanStartDate(LocalDateTime getStartDate, LocalDateTime getEndDate ) {
        if(getEndDate == null) return true;
        return getEndDate.isAfter(getStartDate);
    }

    public static LocalDateTime convertLocalDateToLocalDateTime(LocalDate date) {
        return date.atTime(0, 0, 0);
    }
}
