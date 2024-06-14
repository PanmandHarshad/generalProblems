package patterns.creational.builder.director.car;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int color;

    public Car(String brand, String model, int year, int color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }
}
