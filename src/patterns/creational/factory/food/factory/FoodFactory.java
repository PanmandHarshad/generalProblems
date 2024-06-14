package patterns.creational.factory.food.factory;

import patterns.creational.factory.food.base.Food;
import patterns.creational.factory.food.products.Burger;
import patterns.creational.factory.food.products.Pizza;
import patterns.creational.factory.food.products.Salad;

public class FoodFactory {

    public Food getFood(String foodType) {
        if (foodType == null) {
            return null;
        }

        switch (foodType) {
            case "pizza":
                return new Pizza();
            case "burger":
                return new Burger();
            case "salad":
                return new Salad();
            default:
                throw new IllegalArgumentException("Unknown food type: " + foodType);
        }
    }
}
