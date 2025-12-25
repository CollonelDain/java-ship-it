package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private ArrayList<T> parcels;
    private int maxWeight;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }

    public boolean addParcels(T parcel) {
        if (this.currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Посылка не помещается в эту коробку!\n");
            return false;
        }

        parcels.add(parcel);
        this.currentWeight += parcel.getWeight();
        System.out.println("Посылка добавлена в коробку.\n");
        return true;
    }

    public ArrayList<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public void printContents() {
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.\n");
            return;
        }

        System.out.println("Посылки:");

        for (int i = 0; i < parcels.size(); i++) {
            Parcel parcel = parcels.get(i);
            System.out.printf("%d. Посылка <<%s>>{вес: %d, адрес: %s}\n",
                    i + 1,
                    parcel.getDescription(),
                    parcel.getWeight(),
                    parcel.getDeliveryAddress()
            );
        }
        System.out.println();
    }
}
