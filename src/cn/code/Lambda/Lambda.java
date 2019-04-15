package cn.code.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("In java8 "));
        List features = Arrays.asList("ddd","dddd","asdadas");
        features.forEach(n-> System.out.println(n));
    }
}
