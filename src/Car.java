public class Car {
    private static int nextId = 0;
    private int id;
    private String brand;
    private String model;
    private int hp;
    private int weight;
    private boolean turboMod;

    public Car() {
        id = nextId++;
        brand = "Porsche";
        model = "911 Dakar";
        hp = 480;
        weight = 1680;
        turboMod = false;
    }

    public Car(String brand, String model) {
        id = nextId++;
        this.brand = brand;
        this.model = model;
        hp = 200;
        weight = 1000;
        turboMod = false;
    }

    public Car(String brand, String model, int hp, int weight, boolean turboMod) {
        id = nextId++;
        this.brand = brand;
        this.model = model;
        this.hp = hp;
        this.weight = weight;
        this.turboMod = turboMod;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return brand + " " + model;
    }

    public void info() {
        System.out.printf("%s has %s HP. Weighs %s. %s modified.%n", this.getName(), hp, weight, turboMod ? "Turbo" : "Not");
    }

    // modify car by adding a turbine, if not modified.
    public void modify() {
        if (turboMod) {
            System.out.printf("%s is already modified.%n", this.getName());
        } else {
            turboMod = true;
            hp += 0.3 * hp;
            weight += 20;
            System.out.printf("%s modified.%n", this.getName());
        }
    }

    // modify car by adding turbines, if not modified.
    public void modify(int turbines) {
        if (turboMod) {
            System.out.printf("%s is already modified.%n", this.getName());
        } else if (turbines > 4) {
            System.out.printf("%s is too many for modification of %s.%n", turbines, this.getName());
        } else if (turbines < 1) {
            System.out.printf("there must be at least 1 turbine to modify %s.%n", this.getName());
        } else {
            turboMod = true;
            hp += hp * (0.1 + 0.2 * turbines);
            weight += 20 * turbines;
            System.out.printf("%s modified.%n", this.getName());
        }
    }

    public boolean isMorePowerful(Car car) {
        return (this.hp > car.hp);
    }

    // built a monster car from 2 other cars
    public static Car builtAMonster(Car car1, Car car2, String monsterModel) {
        int monsterHP = (int) ((car1.hp + car2.hp) * 0.9);
        int monsterWeight = car1.weight + car2.weight;
        boolean monsterTurbo = car1.turboMod || car2.turboMod;
        return new Car("Monster", monsterModel, monsterHP, monsterWeight, monsterTurbo);
    }

    // вкладений клас
    public static class Engine {
        private double volume;
        private String type;

        public Engine(double volume, String type) {
            this.volume = volume;
            this.type = type;
        }

        public void info() {
            System.out.printf("Engine volume: %s, type: %s%n", volume, type);
        }
    }
}