package ru.javawebinar.topjava.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Meal {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
}
