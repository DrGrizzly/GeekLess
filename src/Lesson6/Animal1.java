package Lesson6;

public class Animal1 {

    protected String name;
    private String color;
    protected int age;

    public Animal1(String name, String color, int age){
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Animal1(String name){
        this(name,"Серомалина",1);
    }

    public Animal1(){
        this(null);
    }

    @Override
    public String toString(){
        return "{" + "name='"+name+'\'';
    }
}
