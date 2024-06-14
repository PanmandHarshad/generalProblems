package predictions.vehicle;

public class Vehicle {
    int speed = 60;
    static int wheels = 4;

    public void drive() {
        System.out.println("Driving at " + speed + " mph");
    }

    public static void honk() {
        System.out.println("HONK!");
    }
}
