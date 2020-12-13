package Homework6;

import java.util.Random;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public Dog(String name, float runLimit, float swimLimit, float jumpLimit) {
        super(name);
        this.runMaxLimit = (float) this.randomMinMax(runLimit - this.getRandomRangeRun(), runLimit + this.getRandomRangeRun());
        this.jumpMaxLimit = (float) this.randomMinMax(jumpLimit - this.getRandomRangeJump(), jumpLimit + this.getRandomRangeJump());
        this.swimMaxLimit = (float) this.randomMinMax(swimLimit - this.getRandomRangeSwim(), swimLimit + this.getRandomRangeSwim());
    }

    @Override
    public void run(float distance) {
        if (distance >= 0)
            System.out.printf("%s run: %b %n", this.getName(), (distance <= runMaxLimit));
        else System.out.println("Некорректное значение");
    }

    @Override
    public void swim(float distance) {
        if (distance >= 0)
            System.out.printf("%s swim: %b %n", this.getName(), (distance <= swimMaxLimit));
        else System.out.println("Некорректное значение");
    }

    @Override
    public void jump(float distance) {
        if (distance >= 0)
            System.out.printf("%s jump: %b %n", this.getName(), (distance <= jumpMaxLimit));
        else System.out.println("Некорректное значение");
    }

}
