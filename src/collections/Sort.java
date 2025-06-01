package collections;

import car.Taxi;

import java.util.*;
import java.util.stream.Collectors;

public class Sort {

    // Анонімний клас
    public static Collection<Taxi> anonymSort(Collection<Taxi> collection) {
        List<Taxi> list = new ArrayList<>(collection);
        list.sort(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi t1, Taxi t2) {
                return Integer.compare(t1.getFare(), t2.getFare());
            }
        });
        return list;
    }

    // Лямбда
    public static Collection<Taxi> lambdaSort(Collection<Taxi> collection) {
        List<Taxi> list = new ArrayList<>(collection);
        list.sort((t1, t2) -> Integer.compare(t1.getFare(), t2.getFare()));
        return list;
    }

    // Посилання на метод
    public static Collection<Taxi> referenceSort(Collection<Taxi> collection) {
        return collection.stream()
                .sorted(Comparator.comparingInt(Taxi::getFare))
                .collect(Collectors.toList());
    }
}
