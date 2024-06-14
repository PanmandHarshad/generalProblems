package patterns.creational.builder.director.car;

public class CarDirector {
    private CarBuilder carBuilder;

    public CarDirector(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car constructSportCar() {
        return carBuilder
                .setBrand("Ferrari")
                .setModel("F430")
                .setYear(2022)
                .setColor("Red")
                .build();
    }

    public Car constructFamilyCar() {
        return carBuilder
                .setBrand("Toyota")
                .setModel("Camry")
                .setYear(2021)
                .setColor("Silver")
                .build();
    }
}
