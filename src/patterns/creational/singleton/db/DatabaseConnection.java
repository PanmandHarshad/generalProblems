package patterns.creational.singleton.db;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;

    private DatabaseConnection() {
        System.out.println("New database connection is created");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public static DatabaseConnection getImprovedInstance() {
        DatabaseConnection connection = instance;
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                connection = instance;
                if (instance == null) {
                    instance = connection = new DatabaseConnection();
                }
            }
        }
        return connection;
    }

    public void connect() {
        System.out.println("Connecting to the database");
    }
}
