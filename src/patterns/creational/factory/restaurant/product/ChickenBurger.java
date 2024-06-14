package patterns.creational.factory.restaurant.product;

import patterns.creational.factory.restaurant.base.Burger;
import patterns.creational.factory.restaurant.base.Restaurant;
import patterns.creational.factory.restaurant.burgers.Chicken;

public class ChickenBurger extends Restaurant {
    @Override
    public Burger createBurger() {
        System.out.println("Creating chicken burger");
        return new Chicken();
    }
}
