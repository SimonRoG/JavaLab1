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
}
