import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> carList = new ArrayList<>();

//        Car car = null;
//        car.brand = "VW";
//        car.model = "Polo";
//        car.hp = 125;
//        car.weight = 1250;
//        car.turboMod = false;

        carList.add(new Car());
        carList.add(new Car("Semen-Mobil", "2004"));
        carList.add(new Car("Königsegg", "CC850", 1385, 1385, true));

        Car smallMonster = Car.builtAMonster(carList.getFirst(), carList.get(1), "SP1");
        carList.add(smallMonster);

        carList.get(1).info();
        carList.get(1).modify();
        System.out.println();

        for (Car car : carList) {
            car.info();
            System.out.println();
        }

        System.out.printf("%s is %s powerful then %s%n%n",
                carList.getFirst().getName(),
                carList.getFirst().isMorePowerful(carList.get(1)) ? "more" : "less",
                carList.get(1).getName());

        Car monster = Car.builtAMonster(carList.getFirst(), carList.get(2), "SP2");
        carList.add(monster);
        monster.info();
        System.out.println();

        carList.get(smallMonster.getId()).modify(4);
        carList.get(smallMonster.getId()).info();
    }
}