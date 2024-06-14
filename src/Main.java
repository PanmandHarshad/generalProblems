import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    static String str;
    public void Main(){
        System.out.println("inside Constructor");
        str = "Hello world";
    }
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(str);
//        PriorityQueue toDo = new PriorityQueue();
//        toDo.add("Dishesh");
//        toDo.add("Laundry");
//        toDo.add("bills");
//        toDo.offer("bills");
//
//        System.out.println(toDo.size()  + " " + toDo.poll());
//        System.out.println(" "  + toDo.peek() + " " + toDo.poll());
//        System.out.println(" " + toDo.poll() + " " + toDo.poll());
    }
}
