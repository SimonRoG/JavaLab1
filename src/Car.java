public class Car {
    String brand;
    String model;
    int horsePower;

    public Car() {
        brand = "Porsche";
        model = "911 Dakar";
        horsePower = 480;
    }

    public Car(String brand, String model, int horsePower) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public void info() {
        System.out.printf("%s %s has %s HP%n", brand, model, horsePower);
    }
}
