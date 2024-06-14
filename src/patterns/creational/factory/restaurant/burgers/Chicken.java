package patterns.creational.factory.restaurant.burgers;

import patterns.creational.factory.restaurant.base.Burger;

public class Chicken implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicken burger");
    }
}
