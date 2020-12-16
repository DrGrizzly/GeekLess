package Lesson5;

public class Cat {
    //свойства
    String  name;
    String  color;
    int     age;
    //конструктор
    public Cat(String name, String color, int age){
        this.name = name.toUpperCase();
        this.color = color != null ? color : null;
        this.age = age;
    }

    public Cat(String name) {
        this(name, null,0);
    }

    public void printInfo(){
        System.out.println(String.format("%s %s",name,color));

        System.err.println("Огонь");
    }

    //поведение класса - методы
    public void voice(){

    }
    public void run(){

    }

}
