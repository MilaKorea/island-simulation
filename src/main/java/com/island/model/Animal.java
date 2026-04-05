package com.island.model;

public abstract class Animal {

    // === Поля (характеристики каждого животного) ===
    protected String name;           // название вида ("Волк", "Кролик")
    protected double weight;         // вес одного животного, кг
    protected int maxPerCell;        // макс. кол-во этого вида на одной клетке
    protected int speed;             // сколько клеток может пройти за ход
    protected double maxFood;        // сколько кг еды нужно для полного насыщения
    protected double currentFood;    // текущий уровень сытости
    protected boolean alive;         // жив ли

    // === Конструктор ===
    public Animal(String name, double weight, int maxPerCell, int speed, double maxFood) {
        this.name = name;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.maxFood = maxFood;
        this.currentFood = maxFood; // рождается сытым
        this.alive = true;
    }

    // === Абстрактные методы — каждый вид реализует по-своему ===
    public abstract void eat(Cell cell);        // поедание
    public abstract Animal reproduce();         // размножение — возвращает детёныша или null

    // === Общий метод для всех — передвижение ===
    public void move() {
        // пока пустой, реализуем позже в engine
    }

    // === Голод: вызывается каждый ход ===
    public void starve() {
        currentFood -= maxFood * 0.1; // теряет 10% сытости за ход
        if (currentFood <= 0) {
            alive = false; // умер от голода
        }
    }

    // === Геттеры ===
    public boolean isAlive() { return alive; }
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public int getMaxPerCell() { return maxPerCell; }
    public int getSpeed() { return speed; }

    @Override
    public String toString() {
        return name;
    }
}
