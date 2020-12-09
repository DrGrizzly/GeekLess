package Homework;

public class Employee {
    String fullName;
    String position;
    String email;
    String telephone;
    float salary;
    int age;

    public Employee(String name, String position, String email, String telephone, float salary, int age){
        this.fullName = name;
        this.position = position;
        this.email = email;
        this.telephone = telephone;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo()
    {
        System.out.println("=== Информация по сотруднику ===");
        System.out.printf("Сотрудник: %s%n",fullName);
        System.out.printf("Возраст:   %d%n",age);
        System.out.printf("Должность: %s%n",position);
        System.out.printf("Эл.почта:  %s%n",email);
        System.out.printf("Телефон:   %s%n",telephone);
        System.out.printf("Зарплата:  %.2f %n",salary);
        System.out.println();
    }
}
