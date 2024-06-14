package predictions.vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();

        System.out.println("Vehicle speed: " + vehicle.speed); // Output: 60
        System.out.println("Vehicle wheels: " + vehicle.wheels); // Output: 4
        vehicle.drive(); // Output: "Car is driving at 100 mph"
        vehicle.honk(); // Output: "HONK!"

        // The openSunroof() method is not accessible through the 'vehicle' reference.
        // vehicle.openSunroof(); // This line would result in a compilation error.
    }
}
