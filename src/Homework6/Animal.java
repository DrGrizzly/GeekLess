package Homework6;

import java.util.Random;

public abstract class Animal {
    private final String name;
    protected float runMaxLimit;
    protected float swimMaxLimit;
    protected float jumpMaxLimit;

    private final float randomRangeRun;
    private final float randomRangeSwim;
    private final float randomRangeJump;

    public String getName()
    {
        return name;
    }

    public float getRandomRangeRun()
    {
        return randomRangeRun;
    }

    public float getRandomRangeSwim()
    {
        return randomRangeSwim;
    }
    public float getRandomRangeJump()
    {
        return randomRangeJump;
    }

    public Animal(String name){
        this.name = name;
        this.randomRangeRun = 100f;
        this.randomRangeJump = 0.1f;
        this.randomRangeSwim = 2;
    }

    public abstract void run(float distance);

    public abstract void swim(float distance);

    public abstract void jump(float distance);

    public double randomMinMax(int min, int max){
        return Math.random() * (max + 1 - min) + min;
    }

    public double randomMinMax(float min, float max){
        return Math.random() * (max + 1 - min) + min;
    }

    @Override
    public String toString(){
        return String.format("Имя: %s %nЛимиты: %nБег: %.2f %nПлавать: %.2f %nПрыгать: %.2f %n",name,runMaxLimit,swimMaxLimit,jumpMaxLimit);
    }
}
