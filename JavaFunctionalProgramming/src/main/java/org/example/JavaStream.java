package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class JavaStream {
    public static void main(String[] args) {

//        List<Integer> s= new ArrayList<>();
//        s.add(1);s.add(2);s.add(3);
//        s.add(4);s.add(5);s.add(6);
//
//        Stream<Integer> myStream=s.stream();
//
////        map returns output as stream
//        Stream<Integer> op=myStream.map((x)->{
//            System.out.println(x);
//            return x*2;
//        });
//
        List<List<Integer>> listOfList=new ArrayList<>();

        listOfList.add(Arrays.asList(31,62,35,54,57,82));
        listOfList.add(Arrays.asList(11,22,32,4,25,21));
        listOfList.add(Arrays.asList(12,22,234,4,15,232));
//        you can also apply flatmap also
//        like this you can use flatmap
        listOfList.stream().flatMap((value)->{
            return value.stream();
        }).forEach((x)->{
            if (x%2!=0)
                System.out.println(x);
        });
        System.out.println(listOfList);
    }
}
