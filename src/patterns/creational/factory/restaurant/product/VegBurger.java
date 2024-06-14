package patterns.creational.factory.restaurant.product;

import patterns.creational.factory.restaurant.burgers.Veg;
import patterns.creational.factory.restaurant.base.Burger;
import patterns.creational.factory.restaurant.base.Restaurant;

public class VegBurger extends Restaurant {
    @Override
    public Burger createBurger() {
        System.out.println("Creating veg burger");
        return new Veg();
    }
}
