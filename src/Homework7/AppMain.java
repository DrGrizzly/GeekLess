package Homework7;

import java.util.Random;

public class AppMain {
    static Random rnd = new Random();

    public static void main(String[] args) {
        Plate plate1 = new Plate(rnd.nextInt(150));
        Plate plate2 = new Plate(rnd.nextInt(150));
        Plate[] arrPlate = {plate1, plate2, new Plate(rnd.nextInt(50))};
        Cat cat1 = new Cat("Вася");
        Cat cat2 = new Cat("Мурзик");
        Cat[] arrCat = {cat1, cat2, new Cat("Том")};
        //Посмотрим сколько еды в тарелках
        for (Plate plate : arrPlate) {
            plate.printInfo();
        }

        for (int i = 0; i < arrPlate.length; i++) {
            for (Cat cat : arrCat) {
                while (cat.isHungry() && arrPlate[i].getFood() > 0) {
                    cat.eat(arrPlate[i]);
                }
                cat.printInfo();
            }
            System.out.print("Тарелка " + (i + 1) + " - ");
            arrPlate[i].printInfo();

        }


    }
}
