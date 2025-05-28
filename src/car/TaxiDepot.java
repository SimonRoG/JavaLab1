package car;

import java.util.ArrayList;
import java.util.Comparator;

public class TaxiDepot {
    private String name;
    // агрегування класів TaxiDepot -> Taxi
    private ArrayList<Taxi> taxis;

    public TaxiDepot(String name) {
        this.name = name;
        taxis = new ArrayList<>();
    }

    public void add(Taxi taxi) {
        taxis.add(taxi);
    }

    public void info() {
        System.out.printf("Taxi depot: %s%n", name);
        for (Taxi taxi : taxis) {
            taxi.info();
            System.out.println();
        }
    }

    // відшукання Taxi (елемента/-ів) які дорожчі від...
    public void showTaxiWithFareAbove(int minFare) {
        // локальний клас
        class FareFilter {
            void show() {
                System.out.printf("Taxis with fare above %s:%n", minFare);

                for (Taxi taxi : taxis)
                    if (taxi.getFare() > minFare)
                        System.out.printf("%s: %s%n", taxi.getName(), taxi.getFare());
            }
        }
        FareFilter filter = new FareFilter();
        filter.show();
    }

    // відшукання Taxi з максимальною вартістю проїзда
    public Taxi findMaxFareTaxi() {
        Taxi maxFareTaxi = null;
        int maxFare = Integer.MIN_VALUE;
        for (Taxi taxi : taxis)
            if (taxi.getFare() > maxFare) {
                maxFare = taxi.getFare();
                maxFareTaxi = taxi;
            }
        return maxFareTaxi;
    }

    // сортування Taxi в депо
    public void sortByFare() {
        taxis.sort(Comparator.comparingInt(Taxi::getFare));
    }
}
