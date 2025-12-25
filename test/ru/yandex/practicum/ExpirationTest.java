package ru.yandex.practicum;


import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;


public class ExpirationTest {
    private static PerishableParcel parcelPerishable;

    @Test
    public void shouldReturn20ForStandardParcelAndWeight10() {
        parcelPerishable = new PerishableParcel(
                "Standard 1",
                10,
                "Address standard 1",
                (byte) 10,
                10
        );

        assertTrue(parcelPerishable.isExpired(30));
        assertFalse(parcelPerishable.isExpired(20));
        assertFalse(parcelPerishable.isExpired(10));
    }

    @Test
    void testIsExpiredNotExpired() {
        int timeToLive = 5;
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                10,
                "Address perishable 1",
                (byte) 1,
                timeToLive
        );
        int currentDay = 5;

        boolean result = parcelPerishable.isExpired(currentDay);

        assertFalse(result, "Посылка не должна быть просрочена");
    }

    @Test
    void testIsExpiredExpired() {
        int timeToLive = 3;
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                10,
                "Address perishable 1",
                (byte) 1,
                timeToLive
        );
        int currentDay = 10;

        boolean result = parcelPerishable.isExpired(currentDay);

        assertTrue(result, "Посылка должна быть просрочена");
    }

    @Test
    void testIsExpiredBoundaryLastDay() {
        int timeToLive = 7;
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                10,
                "Address perishable 1",
                (byte) 5,
                timeToLive
        );
        int currentDay = 12;

        boolean result = parcelPerishable.isExpired(currentDay);

        assertFalse(result, "В последний день срок годности не истек");
    }

    @Test
    void testIsExpiredBoundaryOneDayAfter() {
        int timeToLive = 7;
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                5,
                "Address perishable 1",
                (byte) 5,
                timeToLive
        );
        int currentDay = 13;

        boolean result = parcelPerishable.isExpired(currentDay);

        assertTrue(result, "Срок годности истек на следующий день после крайнего");
    }
}
