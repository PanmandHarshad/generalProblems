package patterns.creational.factory.food.products;

import patterns.creational.factory.food.base.Food;

public class Burger implements Food {
    @Override
    public void prepare() {
        System.out.println("Grilling burger patty and adding condiments.");
    }

    @Override
    public void serve() {
        System.out.println("Serving burger with lettuce and tomato.");
    }
}
