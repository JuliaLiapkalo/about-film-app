package com.liapkalo.profitsoft.directorfilm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {

     String name;
     String genre;
     int releaseYear;
     List<String> mainActors;
     Director director;

    public Object getAttribute(String attributeName) {
        try {
            Field field = getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("No {} field in Film entity!", e.getMessage());
            throw new RuntimeException();
        }
    }


}
