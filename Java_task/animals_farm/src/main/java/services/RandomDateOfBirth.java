package services;

import Infrastructure.enums.Types;

import java.time.LocalDate;
import java.util.Random;

public class RandomDateOfBirth {

    private static final Random random = new Random();

    public static LocalDate getDate() {
        LocalDate localMinDate = LocalDate.of(2020, 10, 30);
        LocalDate localMaxDate = LocalDate.of(2023, 12, 30);
        long minDay = localMinDate.toEpochDay();
        long maxDay = localMaxDate.toEpochDay();
        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay);
    }

    public static Types getRndType() {
        return Types.values()[random.nextInt(Types.values().length)];
    }
}
