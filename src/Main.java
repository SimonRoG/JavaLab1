import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // масив об’єктів класу
        ArrayList<Car> carList = new ArrayList<>();

        // створення класу без конструктора класу.
        // Car car = null;
        // car.brand = "VW";
        // car.model = "Polo";
        // car.hp = 125;
        // car.weight = 1250;
        // car.turboMod = false;
        // carList.add(car);

        // наповнення масива об’єктів класу
        // конструктор за замовчуванням
        carList.add(new Car());
        // конструктор неповний
        carList.add(new Car("Semen-Mobil", "2004"));
        // конструктор повний
        carList.add(new Car("Königsegg", "CC850", 1385, 1385, true));

        // розрахункові дії, статичний метод: створення авто з 2 інших авто
        Car monster = Car.builtAMonster(carList.getFirst(), carList.get(1), "SP1");
        carList.add(monster);

        for (Car car : carList) {
            car.info();
        }
        System.out.println();

        // розрахункові дії: покращення авто
        carList.get(1).modify();
        carList.get(1).info();
        System.out.println();

        // розрахункові дії: порвняня одного авто з іншим
        System.out.printf("%s is %s powerful then %s%n%n",
                carList.getFirst().getName(),
                carList.getFirst().isMorePowerful(carList.get(1)) ? "more" : "less",
                carList.get(1).getName());

        // розрахункові дії: приклад визначення методу з одним іменем, але різним списком аргументів
        carList.get(monster.getId()).modify(4);
        carList.get(monster.getId()).info();
    }
}