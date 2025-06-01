package collections;

import car.Taxi;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaxiCollection {
    private Collection<Taxi> taxis;

    public TaxiCollection() {
        taxis = new ArrayList<>();
    }

    public TaxiCollection(Collection<Taxi> taxis) {
        this.taxis = taxis;
    }

    public void add(Taxi taxi) {
        taxis.add(taxi);
    }

    public void info() {
        for (Taxi taxi : taxis) {
            taxi.info();
        }
    }

    public Collection<Taxi> getTaxis() {
        return taxis;
    }

    // Пошук за умовою
    public Optional<Taxi> findTaxi(Predicate<Taxi> condition) {
        return taxis.stream().filter(condition).findFirst();
    }

    // Унікальні елементи
    public Set<Taxi> getUnique() {
        return new HashSet<>(taxis);
    }

    // Порівняння за критерієм
    public List<Taxi> compareByHP() {
        return taxis.stream().sorted(Comparator.comparingInt(Taxi::getHp)).toList();
    }

    // Фільтрація за ознакою
    public List<Taxi> filterByTurbo() {
        return taxis.stream().filter(Taxi::isTurboMod).toList();
    }

    // Середнє значення
    public double averageWeight() {
        return taxis.stream().mapToInt(Taxi::getWeight).average().orElse(0);
    }
}