package SourceCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamsDemo {
    public static void somemethod() {
        //java 7
        List<Integer> arr = new ArrayList<Integer>();
        for(int i = 1; i < 501; i++) {
            arr.add(i);
        }

        List<Integer> squares7 = new ArrayList<Integer>();
        for(int x : arr ) {
            squares7.add( x * x);
        }

        //java 8
        //IntStream range = IntStream.range(1, 501);
        Stream<Integer> squares = (Stream<Integer>) arr.stream().map(x -> {
            System.out.println(x);
            return x* x;
        });
        List<Integer> squaresList = squares.collect(Collectors.toList());

        Stream<Integer> squares2 = arr.stream().map((x) -> x * x).filter((x) -> x > 100);
        List<Integer> squares8 =  squares2.collect(Collectors.toList());

        Stream<Integer> out = arr.parallelStream().filter((x) -> x % 2 == 0 ).map((x) -> x * x);
        List<Integer> squaresParallel = out.collect(Collectors.toList());

    }

    public static void main(String[] args) {
        somemethod();
    }
}
