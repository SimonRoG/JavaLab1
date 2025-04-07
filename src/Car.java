public class Car {
    private String brand;
    private String model;
    int hp;
    boolean turboMod;

    public Car() {
        brand = "Porsche";
        model = "911 Dakar";
        hp = 480;
        turboMod = false;
    }

    public Car(String brand, String model, int hp, boolean turboMod) {
        this.brand = brand;
        this.model = model;
        this.hp = hp;
        this.turboMod = turboMod;
    }

    public String getName() {
        return brand + " " + model;
    }

    public void info() {
        System.out.printf("%s %s has %s HP. %s modified.%n", brand, model, hp, turboMod ? "Turbo" : "Not" );
    }

    public void modify() {
        if (turboMod) {
            System.out.printf("%s %s is already modified.%n", brand, model);
        } else {
            turboMod = true;
            hp += 0.3 * hp;
            System.out.printf("%s %s modified.%n", brand, model);
        }
    }

    public boolean isMorePowerful(Car car) {
        return this.hp > car.hp;
    }
}
