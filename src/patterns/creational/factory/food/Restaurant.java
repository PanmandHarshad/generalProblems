package patterns.creational.factory.food;

import patterns.creational.factory.food.factory.FoodFactory;
import patterns.creational.factory.food.base.Food;

public class Restaurant {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        Food burger = foodFactory.getFood("burger");
        burger.prepare();

        Food pizza = foodFactory.getFood("pizza");
        pizza.serve();
    }
}
