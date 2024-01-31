package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamPipelineAndAdvancedThings {
    public static void main(String[] args) {

//        stream reduction is one of the most important operation inside java streams
//        reduce and collect methods are used to customize reduction mechanism of stream in java
        // we have to use pipeline functions as we can reuse same stream again
        Random random=new Random();
        IntStream intStream= random.ints(1,50).limit(12).sorted();
//        intStream.forEach((x)->{
//            System.out.println(x);
//        });

//        optional type come to play when we dont know datatype of solution at once
//        reduce the values to one param
        OptionalInt value= intStream.reduce((a, b)->{
//            System.out.println("a : "+a+" b : "+b);
            return  a+b;});
        System.out.println(value);

//        we can also able to reduce values into 2 parameters
        IntStream intStream1= random.ints(1,50).limit(12).sorted();
        Optional<Integer> anwser= Optional.of((intStream1.reduce(100, (a, b) -> {
            System.out.println("a : " + a + " b : " + b);
            return a + b;
        })));
        System.out.println(anwser);

        IntStream intStream4= random.ints(1,50).limit(12).sorted();
//        collect function is used to collect stream and convert into list
    }
}
