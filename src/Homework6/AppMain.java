package Homework6;

public class AppMain {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Тузик",500f,10f,0.5f);
        System.out.println(dog1);

        dog1.jump(0.3f);
        dog1.run(130f);
        dog1.swim(5f);

        Cat cat1 = new Cat("Феликс",200f,2f);
        System.out.println(cat1);
        cat1.jump(1f);
        cat1.run(100f);
        cat1.swim(30f);
    }
}
