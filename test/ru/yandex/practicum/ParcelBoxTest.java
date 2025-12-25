package ru.yandex.practicum;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ru.yandex.practicum.delivery.Parcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {
    private static ParcelBox<Parcel> parcelBox;

    @BeforeEach
    void setUp() {
        parcelBox = new ParcelBox<>(10);
    }

    @Test
    void testAddParcelWithinWeightLimit() {
        Parcel parcel = new PerishableParcel(
                "Perishable 1",
                3,
                "Perishable address 1",
                (byte) 1,
                10
        );

        boolean result = parcelBox.addParcels(parcel);

        assertTrue(result, "Посылка должна быть успешно добавлена");
        assertEquals(1, parcelBox.getAllParcels().size(), "В коробке должна быть одна посылка");
        assertEquals(3, getCurrentWeight(parcelBox), "Текущий вес коробки должен быть равен 3");
    }

    @Test
    void testAddMultipleParcelsWithinWeightLimit() {
        Parcel parcel1 = new PerishableParcel(
                "Perishable 1",
                4,
                "Perishable address 1",
                (byte) 1,
                5);
        Parcel parcel2 = new PerishableParcel(
                "Perishable 2",
                3,
                "Perishable address 2",
                (byte) 1,
                5);
        Parcel parcel3 = new PerishableParcel(
                "Perishable 3",
                2,
                "Perishable address 3",
                (byte) 1,
                5);

        boolean result1 = parcelBox.addParcels(parcel1);
        boolean result2 = parcelBox.addParcels(parcel2);
        boolean result3 = parcelBox.addParcels(parcel3);

        assertTrue(result1, "Первая посылка должна быть добавлена");
        assertTrue(result2, "Вторая посылка должна быть добавлена");
        assertTrue(result3, "Третья посылка должна быть добавлена");
        assertEquals(3, parcelBox.getAllParcels().size(), "В коробке должно быть 3 посылки");
        assertEquals(9, getCurrentWeight(parcelBox), "Текущий вес коробки должен быть равен 9");
    }

    @Test
    void testAddParcelExceedsMaxWeight() {
        Parcel heavyParcel = new PerishableParcel(
                "Perishable heavy 1",
                15,
                "Perishable address 1",
                (byte) 1,
                10
        );

        boolean result = parcelBox.addParcels(heavyParcel);

        assertFalse(result, "Посылка не должна быть добавлена (превышение максимального веса)");
        assertEquals(0, parcelBox.getAllParcels().size(), "Коробка должна остаться пустой");
        assertEquals(0, getCurrentWeight(parcelBox), "Текущий вес коробки должен остаться 0");
    }

    @Test
    void testAddParcelExceedsMaxWeightWithExistingParcels() {
        Parcel parcel1 = new PerishableParcel(
                "Perishable 1",
                6,
                "Perishable address 1",
                (byte) 1,
                5
        );
        Parcel parcel2 = new PerishableParcel(
                "Perishable 2",
                5,
                "Perishable address 2",
                (byte) 1,
                5
        );

        boolean result1 = parcelBox.addParcels(parcel1);
        boolean result2 = parcelBox.addParcels(parcel2);

        assertTrue(result1, "Первая посылка должна быть добавлена");
        assertFalse(result2, "Вторая посылка не должна быть добавлена (превышение веса)");
        assertEquals(1, parcelBox.getAllParcels().size(), "В коробке должна быть только одна посылка");
        assertEquals(6, getCurrentWeight(parcelBox), "Текущий вес коробки должен быть 6");
    }

    @Test
    void testAddParcelExactMaxWeight() {
        Parcel exactWeightParcel = new PerishableParcel(
                "Perishable 1",
                10,
                "Perishable address 1",
                (byte) 1,
                10
        );

        boolean result = parcelBox.addParcels(exactWeightParcel);

        assertTrue(result, "Посылка должна быть добавлена (вес равен максимальному)");
        assertEquals(1, parcelBox.getAllParcels().size(), "В коробке должна быть одна посылка");
        assertEquals(10, getCurrentWeight(parcelBox), "Текущий вес коробки должен быть равен 10");
    }

    @Test
    void testAddParcelZeroWeight() {
        Parcel zeroWeightParcel = new PerishableParcel(
                "Perishable 0",
                0,
                "Perishable address 0",
                (byte) 1,
                10
        );

        boolean result = parcelBox.addParcels(zeroWeightParcel);

        assertTrue(result, "Посылка с весом 0 должна быть добавлена");
        assertEquals(1, parcelBox.getAllParcels().size(), "В коробке должна быть одна посылка");
        assertEquals(0, getCurrentWeight(parcelBox), "Текущий вес коробки должен быть 0");
    }

    @Test
    void testAddParcelOneWeightAfterAlmostFull() {
        Parcel parcel1 = new PerishableParcel(
                "Perishable 1",
                9,
                "Perishable address 1",
                (byte) 1,
                5
        );
        Parcel parcel2 = new PerishableParcel(
                "Perishable 2",
                1,
                "Perishable address 2",
                (byte) 1,
                5
        );

        boolean result1 = parcelBox.addParcels(parcel1);
        boolean result2 = parcelBox.addParcels(parcel2);

        assertTrue(result1, "Первая посылка должна быть добавлена");
        assertTrue(result2, "Вторая посылка (вес 1) должна быть добавлена");
        assertEquals(2, parcelBox.getAllParcels().size(), "В коробке должно быть 2 посылки");
        assertEquals(10, getCurrentWeight(parcelBox), "Текущий вес коробки должен быть 10");
    }

    private int getCurrentWeight(ParcelBox<Parcel> box) {
        int totalWeight = 0;
        for (Parcel parcel : box.getAllParcels()) {
            totalWeight += parcel.getWeight();
        }
        return totalWeight;
    }
}