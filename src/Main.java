import car.*;
import collections.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Taxi>  taxis = List.of(new Taxi[]{
                new Taxi("Ford", "Mondeo", 180, 1400, true, 50),
                new Taxi("Mercedes", "E-class", 250, 1600, true, 65),
                new Taxi("Mercedes", "E-class", 250, 1600, true, 65),
                new Taxi("Skoda", "Superb", 170, 1350, false, 55)
        });

        // Колекція
        TaxiCollection collection = new TaxiCollection();
        for (Taxi taxi : taxis)
            collection.add(taxi);

        System.out.println("Розробити клас, в якому агрегуються об’єкти класу у вигляді колекції інтерфейсного типу `Collection`.");
        collection.info();
        System.out.println();

        System.out.println("відшукувати об’єкт, який відповідає заданим вимогам:");
        collection.findTaxi(t -> t.getHp() > 180).ifPresent(Taxi::info);
        System.out.println();

        System.out.println("створювати колекцію, яка містить тільки унікальні елементи");
        collection.getUnique().forEach(Taxi::info);
        System.out.println();

        System.out.println("порівнювати об’єкти за різними критеріями якості");
        collection.compareByHP().forEach(Taxi::info);
        System.out.println();

        System.out.println("виконувати операцію фільтрації об’єктів за заданою ознакою");
        collection.filterByTurbo().forEach(Taxi::info);
        System.out.println();

        System.out.println("виконувати визначення середнього значення кількісної ознаки об’єкта");
        System.out.printf("%.2f%n%n", collection.averageWeight());

        // Сортування
        System.out.println("Розробити метод для сортування об’єктів за вибраним критерієм якості");
        System.out.println("з анонімним класом");
        new TaxiCollection(Sort.anonymSort(collection.getTaxis())).info();
        System.out.println();

        System.out.println("з лямбда-виразом");
        new TaxiCollection(Sort.lambdaSort(collection.getTaxis())).info();
        System.out.println();

        System.out.println("з посиланням на методи");
        new TaxiCollection(Sort.referenceSort(collection.getTaxis())).info();
        System.out.println();

        // Map
        TaxiMap map = new TaxiMap();
        for (Taxi taxi : taxis)
            map.add(taxi);

        System.out.println("Розробити клас, в якому агрегуються об’єкти класу у вигляді колекції інтерфейсного типу `Map`");
        System.out.println("визначати для колекції суму заданої кількісної ознаки");
        System.out.printf("%d%n%n", map.totalFare());

        System.out.println("фільтрувати об’єкти колекції за вибраною ознакою");
        map.filterByFare(55).values().forEach(Taxi::info);
        System.out.println();

        System.out.println("видаляти з колекції об’єкти, які не відповідають заданому критерію");
        map.removeIf(Car::isTurboMod);
        map.info();
        System.out.println();

        // Сортування за кількома ознаками
        map = new TaxiMap(); // відновлення
        for (Taxi taxi : taxis)
            map.add(taxi);

        System.out.println("Розробити метод для сортування об’єктів послідовно за кількома вибраними ознаками");
        System.out.println("з анонімним класом");
        new TaxiMap(MultiSort.anonymSort((map.getTaxiMap().values()))).info();
        System.out.println();

        System.out.println("з лямбда-виразом");
        new TaxiMap(MultiSort.lambdaSort((map.getTaxiMap().values()))).info();
        System.out.println();

        System.out.println("з посиланням на методи");
        new TaxiMap(MultiSort.referenceSort((map.getTaxiMap().values()))).info();
        System.out.println();
    }
}
