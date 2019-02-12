package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    public static void main(String[] args) {
        getWithExceeded(MEALS, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> mealList, int caloriesPerDay) {
        return getWithExceeded(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    private static List<MealWithExceed> getWithExceeded(Collection<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().getDayOfYear())).values()
                .stream().flatMap(list -> list.stream()
                        .map(meal -> new MealWithExceed(meal, list.stream()
                                .map(Meal::getCalories)
                                .reduce((c1, c2) -> c1 + c2)
                                .orElse(0) > caloriesPerDay)))
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}
