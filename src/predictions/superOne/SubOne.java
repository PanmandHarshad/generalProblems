package predictions.superOne;

public class SubOne extends SuperOne {
    int i = 1000;
    static int j = 2000;

    public void show() {
        System.out.println("sub show");
    }

    public static void execute() {
        System.out.println("sub execute");
    }
}
