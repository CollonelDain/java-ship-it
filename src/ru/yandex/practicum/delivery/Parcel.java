package ru.yandex.practicum.delivery;

public abstract class Parcel {
    private String description;
    private int weight;
    private String deliveryAddress;
    private byte sendDay;

    public Parcel(String description, int weight, String deliveryAddress, byte sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public byte getSendDay() {
        return sendDay;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public void packageItem() {
        System.out.printf("Посылка <<%s>> упакована\n", this.description);
    }

    public void deliver() {
        System.out.printf("Посылка <<%s>> доставлена по адресу %s\n", this.description, this.deliveryAddress);
    }

    public abstract int calculateDeliveryCost();

}
