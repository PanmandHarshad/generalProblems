package patterns.creational.factory.restaurant.base;

abstract public class Restaurant {

    public Burger orderBurger(){
        System.out.println("Ordering the new burger");
        Burger burger = createBurger();
        burger.prepare();
        return burger;
    }

    public abstract Burger createBurger();
}
