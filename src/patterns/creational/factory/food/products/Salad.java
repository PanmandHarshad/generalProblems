package patterns.creational.factory.food.products;

import patterns.creational.factory.food.base.Food;

public class Salad implements Food {
    @Override
    public void prepare() {
        System.out.println("Chopping vegetables and mixing dressing.");
    }

    @Override
    public void serve() {
        System.out.println("Serving fresh garden salad.");
    }
}
