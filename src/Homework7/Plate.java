package Homework7;

public class Plate {
    private int food = 0;

    public Plate() {
        this(0);
    }

    public Plate(int food) {
        this.food = food;
    }

    public void addFood(int food) {
        this.food += (food > 0) ? food : 0;
    }

    public int getFood() {
        return food;
    }

    public int decreaseFood(int food) {
        if (this.food == 0) return 0;
        int val = (food > 0) ? food : 0;      //Не дадим ввести отрицательное
        int count = Math.min(this.food, val); //Возьмем наименьшее
        this.food -= count;

        return count;
    }

    public void printInfo() {
        System.out.println(this);
    }

    public String toString() {
        return "Количество еды в тарелке: " + getFood();
    }

}
