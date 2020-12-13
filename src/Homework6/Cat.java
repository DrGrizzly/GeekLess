package Homework6;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public Cat(String name, float runLimit,float jumpLimit) {
        super(name);
        this.runMaxLimit = (float) this.randomMinMax(runLimit - this.getRandomRangeRun(), runLimit + this.getRandomRangeRun());
        this.jumpMaxLimit = (float) this.randomMinMax(jumpLimit - this.getRandomRangeJump(), jumpLimit + this.getRandomRangeJump());
    }

    @Override
    public void run(float distance) {
        if (distance >= 0)
            System.out.printf("%s run: %b %n", this.getName(), (distance <= runMaxLimit));
        else System.out.println("Некорректное значение");
    }

    @Override
    public void swim(float distance) {
       System.out.println("Кот не умеет плавать");
    }

    @Override
    public void jump(float distance) {
        if (distance >= 0)
            System.out.printf("%s jump: %b %n", this.getName(), (distance <= jumpMaxLimit));
        else System.out.println("Некорректное значение");
    }
}
