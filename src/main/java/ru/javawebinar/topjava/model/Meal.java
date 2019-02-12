package ru.javawebinar.topjava.model;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Meal {
    @Setter
    private Integer id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public boolean isNew() {
        return id == null;
    }
}
