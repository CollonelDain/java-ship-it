package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private final int BASE_COST = 3;
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, byte sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int calculateDeliveryCost() {
        return BASE_COST * getWeight();
    }

    public boolean isExpired(int currentDay) {
        return timeToLive + getSendDay() < currentDay;
    }
}
