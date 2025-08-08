
package com.example.hw03gymlog.database.typeConverters;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


/**
 * Type converter for LocalDateTime to long and vice versa.
 *
 * @author Madison Nolen
 * @since 8/7/2025
 */
public class LocalDateTypeConverter {
    @TypeConverter
    public long convertDateToLong(LocalDateTime date){
        ZonedDateTime zdt = ZonedDateTime.of(date, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    @TypeConverter
    public LocalDateTime convertLongToDate(long epochMilli){
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}

