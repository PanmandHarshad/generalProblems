package test123;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LambdaExpressions {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", null);
        list.stream().filter(Objects::nonNull).toList().forEach(System.out::println);
    }
}
