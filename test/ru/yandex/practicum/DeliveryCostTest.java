package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {
    private static StandardParcel parcelStandard;
    private static FragileParcel parcelFragile;
    private static PerishableParcel parcelPerishable;

    @Test
    public void shouldReturn20ForStandardParcelAndWeight10() {
        parcelStandard = new StandardParcel(
                "Standard 1",
                10,
                "Address standard 1",
                (byte) 10);
        int expectedCost = 20;

        assertEquals(expectedCost, parcelStandard.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn34ForStandardParcelAndWeight17() {
        parcelStandard = new StandardParcel(
                "Standard 1",
                17,
                "Address standard 1",
                (byte) 10);
        int expectedCost = 34;

        assertEquals(expectedCost, parcelStandard.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn0ForStandardParcelAndWeight0() {
        parcelStandard = new StandardParcel(
                "Standard 1",
                0,
                "Address standard 1",
                (byte) 10);
        int expectedCost = 0;

        assertEquals(expectedCost, parcelStandard.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn40ForFragileParcelAndWeight10() {
        parcelFragile = new FragileParcel(
                "Fragile 1",
                10,
                "Address fragile 1",
                (byte) 10);
        int expectedCost = 40;

        assertEquals(expectedCost, parcelFragile.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn68ForFragileParcelAndWeight17() {
        parcelFragile = new FragileParcel(
                "Fragile 1",
                17,
                "Address fragile 1",
                (byte) 10);
        int expectedCost = 68;

        assertEquals(expectedCost, parcelFragile.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn0ForFragileParcelAndWeight0() {
        parcelFragile = new FragileParcel(
                "Fragile 1",
                0,
                "Address fragile 1",
                (byte) 10);
        int expectedCost = 0;

        assertEquals(expectedCost, parcelFragile.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn30ForPerishableParcelAndWeight10() {
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                10,
                "Address perishable 1",
                (byte) 10,
                10
        );
        int expectedCost = 30;

        assertEquals(expectedCost, parcelPerishable.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn51ForPerishableParcelAndWeight17() {
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                17,
                "Address perishable 1",
                (byte) 10,
                10
        );
        int expectedCost = 51;

        assertEquals(expectedCost, parcelPerishable.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn0ForPerishableParcelAndWeight0() {
        parcelPerishable = new PerishableParcel(
                "Perishable 1",
                0,
                "Address perishable 1",
                (byte) 10,
                10
        );
        int expectedCost = 0;

        assertEquals(expectedCost, parcelPerishable.calculateDeliveryCost());
    }

}
