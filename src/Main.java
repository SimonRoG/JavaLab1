import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> carList = new ArrayList<>();

        carList.add(new Car());
        carList.add(new Car("Königsegg", "CC850", 1385, true));

        for (Car car : carList) {
            car.info();
            car.modify();
            car.info();
            System.out.println();
        }

        System.out.printf("%s is %s powerful then %s%n",
                carList.getFirst().getName(),
                carList.getFirst().isMorePowerful(carList.getLast()) ? "more" : "less",
                carList.getLast().getName());
    }
}