import car.Car;
import car.Taxi;
import car.TaxiDepot;

public class Main {
    public static void main(String[] args) {
        // масив
        Car[] cars = {
                new Car("Toyota", "Corolla", 150, 1300, false),
                new Taxi("Ford", "Mondeo", 180, 1400, true, 50),
                new Taxi("Mercedes", "E-class", 250, 1600, true, 65),
                new Car("Audi", "A4", 200, 1450, false),
                new Taxi("Skoda", "Superb", 170, 1350, false, 55)
        };

        for (Car car : cars) {
            car.info();
            if (car instanceof Taxi taxi)
                taxi.drive();
            System.out.println();
        }

        TaxiDepot depot = new TaxiDepot("CityTaxi");
        for (Car car : cars)
            if (car instanceof Taxi taxi)
                depot.add(taxi);

        depot.info();

        depot.showTaxiWithFareAbove(50);
        System.out.println();

        System.out.printf("Max fare: %s%n%n", depot.findMaxFareTaxi().getName());

        depot.sortByFare();
        System.out.printf("Sorted Taxi in depot:%n");
        depot.info();

        Taxi.Engine engine = new Taxi.Engine(2.0, "Diesel");
        engine.info();
        System.out.println();

    }
}
