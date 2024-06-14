package predictions.vehicle;

public class Car extends Vehicle {
    int speed = 100;
    static int wheels = 4;

    public void drive() {
        System.out.println("Car is driving at " + speed + " mph");
    }

    public static void honk() {
        System.out.println("Car honks: BEEP BEEP!");
    }

    public void openSunroof() {
        System.out.println("Sunroof is open");
    }
}
