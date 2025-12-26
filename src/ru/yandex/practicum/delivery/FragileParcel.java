package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {
    private final int BASE_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, byte sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка <<%s>> обёрнута в защитную плёнку\n", getDescription());
        super.packageItem();
    }

    @Override
    public int getBaseCost() {
        return BASE_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка <<%s>> изменила местоположение на %s\n", getDescription(), newLocation);
    }
}

