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
