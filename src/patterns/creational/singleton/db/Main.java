package patterns.creational.singleton.db;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        DatabaseConnection instance1 = DatabaseConnection.getInstance();

        instance.connect();
        instance1.connect();

        System.out.println(instance == instance1);
    }
}
