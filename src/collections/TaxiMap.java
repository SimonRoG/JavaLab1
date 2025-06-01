package collections;

import car.Car;
import car.Taxi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class TaxiMap {
    private Map<Integer, Taxi> taxiMap;

    public TaxiMap() {
        taxiMap = new HashMap<>();
    }

    public TaxiMap(Collection<Taxi> taxis) {
        taxiMap = taxis.stream().collect(Collectors.toMap(Taxi::getId, taxi -> taxi));
    }

    public void add(Taxi taxi) {
        taxiMap.put(taxi.getId(), taxi);
    }

    public void info() {
        taxiMap.values().forEach(Taxi::info);
    }

    public Map<Integer, Taxi> getTaxiMap() {
        return taxiMap;
    }

    // Фільтр
    public Map<Integer, Taxi> filterByFare(int minFare) {
        return taxiMap.entrySet().stream()
                .filter(entry -> entry.getValue().getFare() > minFare)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Видалення за умовою
    public void removeIf(Predicate<Taxi> condition) {
        taxiMap.values().removeIf(condition);
    }

    // Сума
    public int totalFare() {
        return taxiMap.values().stream().mapToInt(Taxi::getFare).sum();
    }
}