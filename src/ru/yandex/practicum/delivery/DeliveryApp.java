package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> parcelsWithTracking = new ArrayList<>();
    private static ParcelBox<StandardParcel> boxStandart = new ParcelBox<>(15);
    private static ParcelBox<FragileParcel> boxFragile = new ParcelBox<>(10);
    private static ParcelBox<PerishableParcel> boxPerishable = new ParcelBox<>(5);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    setNewReportStatus();
                    break;
                case 5:
                    showContentsOfBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.\n");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Задать новую локацию для посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандарт");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Скоропортящаяся");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String description;
        int weight;
        String deliveryAddress;
        byte sendDay;
        try {
            System.out.print("Введите описание посылки: ");
            description = scanner.nextLine();

            System.out.print("Введите вес посылки (целое число): ");
            weight = Integer.parseInt(scanner.nextLine());

            System.out.print("Введите адрес доставки: ");
            deliveryAddress = scanner.nextLine();

            System.out.print("Введите день отправки: ");
            sendDay = Byte.parseByte(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод числа!\n");
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка!\n");
            System.out.println(e.getMessage());
            return;
        }

        Parcel parcel;

        switch (choice) {
            case 1:
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                boxStandart.addParcels((StandardParcel) parcel);
                break;
            case 2:
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                parcelsWithTracking.add((Trackable) parcel);
                boxFragile.addParcels((FragileParcel) parcel);
                break;
            case 3:
                int timeToLive;
                try {
                    System.out.print("Введите срок годности (в днях): ");
                    timeToLive = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод числа!\n");
                    System.out.println(e.getMessage());
                    return;
                }
                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                boxPerishable.addParcels((PerishableParcel) parcel);
                break;
            default:
                System.out.println("Неверный выбор типа посылки!\n");
                return;
        }

        allParcels.add(parcel);
        System.out.println("Посылка успешно принята для доставки.\n");
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int costs = 0;
        for (Parcel parcel : allParcels) {
            costs += parcel.calculateDeliveryCost();
        }

        System.out.printf("Общая сумма доставки <<%s>> рублей\n\n", costs);
    }

    private static void setNewReportStatus() {
        if (parcelsWithTracking.isEmpty()) {
            System.out.println("Нет посылок, поддерживающих трекинг.");
            return;
        }

        for (Trackable parcel : parcelsWithTracking) {
            System.out.printf("Введите текущее местоположение посылки <<%s>>: \n", ((Parcel) parcel).getDescription());
            ((FragileParcel) parcel).reportStatus(scanner.nextLine());
        }

        System.out.println("Статус всех посылок обновлен.\n");
    }

    private static void showContentsOfBox() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 — Стандарт");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Скоропортящаяся");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                boxStandart.printContents();
                break;
            case 2:
                boxFragile.printContents();
                break;
            case 3:
                boxPerishable.printContents();
                break;
            default:
                System.out.println("Неверный выбор типа коробки!\n");
        }
    }
}

