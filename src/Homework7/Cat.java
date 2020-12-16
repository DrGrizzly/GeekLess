package Homework7;

import java.util.Random;

public class Cat {
    private String name;
    private int appetite; //аппетит - сколько он съел
    private int maxFull;  //сытость - сколько нужно еды, чтобы насытиться

    Random rnd = new Random();

    public Cat(String name) {
        this.name = name;
        this.maxFull = rnd.nextInt(20) + 80;
    }

    public boolean eat(Plate plate) {
        int foodCount = rnd.nextInt(1)+5;

        //Если кот наелся, то выходим
        if (!isHungry()) return false;

        appetite += plate.decreaseFood(foodCount);

        return true;
    }

    public boolean isHungry(){
        return (appetite < maxFull); //Если кот еще не наелся, то false
    }

    public void printInfo() {
        System.out.println(this);
    }

    public String toString() {
        return "Кот: " + this.name+" "+(isHungry() ? "голодный": "сытый");
    }
}
