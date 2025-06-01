package collections;

import car.Taxi;

import java.util.*;

public class MultiSort {

    // Анонімний клас
    public static Collection<Taxi> anonymSort(Collection<Taxi> collection) {
        List<Taxi> list = new ArrayList<>(collection);
        list.sort(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi t1, Taxi t2) {
                int result = Integer.compare(t1.getFare(), t2.getFare());
                if (result == 0)
                    result = Integer.compare(t1.getHp(), t2.getHp());
                return result;
            }
        });
        return list;
    }

    // Лямбда
    public static Collection<Taxi> lambdaSort(Collection<Taxi> collection) {
        List<Taxi> list = new ArrayList<>(collection);
        list.sort((t1, t2) -> {
            int result = Integer.compare(t1.getFare(), t2.getFare());
            if (result == 0)
                result = Integer.compare(t1.getHp(), t2.getHp());
            return result;
        });
        return list;
    }

    // Посилання на метод
    public static Collection<Taxi> referenceSort(Collection<Taxi> collection) {
        List<Taxi> list = new ArrayList<>(collection);
        list.sort(Comparator.comparingInt(Taxi::getFare).thenComparingInt(Taxi::getHp));
        return list;
    }
}
