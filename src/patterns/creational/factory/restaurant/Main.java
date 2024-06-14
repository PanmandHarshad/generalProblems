package patterns.creational.factory.restaurant;

import patterns.creational.factory.restaurant.product.VegBurger;
import patterns.creational.factory.restaurant.base.Burger;
import patterns.creational.factory.restaurant.base.Restaurant;
import patterns.creational.factory.restaurant.product.ChickenBurger;

public class Main {

    public static void main(String[] args) {
        Restaurant chickenBurger = new ChickenBurger();
        Burger burger = chickenBurger.createBurger();

        Restaurant vegBurger = new VegBurger();
        Burger burger1 = vegBurger.orderBurger();
    }
}
