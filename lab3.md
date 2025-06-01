# ЛАБОРАТОРНА РОБОТА 3

## "Розробка колекцій з використанням Collection Framework. <br>Використання інтерфейсів, лямбда-виразів та посилань на методи при обробці колекцій"

**Виконав:** Семен Прохода ІП-з31

### Завдання до роботи:

**Варіант 2:** Клас „авто” – клас „таксопарк” – клас „таксі”

1. Розробити клас, в якому агрегуються об’єкти класу у вигляді колекції інтерфейсного типу `Collection`.
    - відшукувати об’єкт, який відповідає заданим вимогам
    - створювати колекцію, яка містить тільки унікальні елементи
    - порівнювати об’єкти за різними критеріями якості
    - виконувати операцію фільтрації об’єктів за заданою ознакою
    - виконувати визначення середнього значення кількісної ознаки об’єкта
2. Розробити метод для сортування об’єктів за вибраним критерієм якості
    - з анонімним класом
    - з лямбда-виразом
    - з посиланням на методи
3. Розробити клас, в якому агрегуються об’єкти класу у вигляді колекції інтерфейсного типу `Map`
    - фільтрувати об’єкти колекції за вибраною ознакою
    - видаляти з колекції об’єкти, які не відповідають заданому критерію
    - визначати для колекції суму заданої кількісної ознаки
4. Розробити метод для сортування об’єктів послідовно за кількома вибраними ознаками
    - з анонімним класом
    - з лямбда-виразом
    - з посиланням на методи

### Лістинг програми:

#### [`Taxi.java`](/src/collections/Taxi.java)

```java
package car;

import java.util.Objects;

// наслідування класу Car -> Taxi
public class Taxi extends Car {
    private int fare;

    public Taxi(String brand, String model, int hp, int weight, boolean turboMod, int fare) {
        super(brand, model, hp, weight, turboMod);
        this.fare = fare;
    }

    public int getFare() {
        return fare;
    }

    public void drive() {
        System.out.printf("%s is driving. Fare: %d UAH%n", getName(), fare);
    }

    @Override
    public void info() {
        super.info();
        System.out.printf("Taxi fare: %d UAH%n", fare);
    }

    // додана перегрузка equals та hashCode для пошуку унікальних Taxi
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taxi)) return false;
        Taxi taxi = (Taxi) o;
        return getName().equals(taxi.getName()) &&
                getHp() == taxi.getHp() &&
                isTurboMod() == taxi.isTurboMod() &&
                getWeight() == taxi.getWeight() &&
                fare == taxi.fare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHp(), isTurboMod(), getWeight(), fare);
    }
}
```

#### [`TaxiCollection.java`](/src/collections/TaxiCollection.java)

```java
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
```

#### [`Sort.java`](/src/collections/Sort.java)

```java
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
```

#### [`TaxiMap.java`](/src/collections/TaxiMap.java)

```java
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
```

#### [`MultiSort.java`](/src/collections/MultiSort.java)

```java
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
```

### Результати тестування класу: