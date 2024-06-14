package predictions.superOne;

public class Main {
    public static void main(String[] args) {
        SuperOne s = new SubOne();
        System.out.println(s.i);
        System.out.println(s.j);
        s.show();
        s.execute();
    }
}
