package patterns.creational.factory.restaurant.burgers;

import patterns.creational.factory.restaurant.base.Burger;

public class Veg implements Burger {

    @Override
    public void prepare() {
        System.out.println("Preparing Veg burger");
    }
}
