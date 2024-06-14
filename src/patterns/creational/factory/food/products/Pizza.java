package patterns.creational.factory.food.products;

import patterns.creational.factory.food.base.Food;

public class Pizza implements Food {
    @Override
    public void prepare() {
        System.out.println("Preparing pizza dough and adding toppings.");
    }

    @Override
    public void serve() {
        System.out.println("Serving pizza with cheese and pepperoni.");
    }
}
