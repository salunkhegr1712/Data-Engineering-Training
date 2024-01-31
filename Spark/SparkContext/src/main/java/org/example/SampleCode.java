package org.example;

import scala.Int;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleCode {
    public static void main(String[] args) {

//        say we have the list
        List<Integer> myList= Arrays.asList(143,34,43,66,4,64,43,2,523,53);

//        so lets create some streams with them
        Stream<Integer> stream=myList.stream();

//        map function is used in order to do one to one mapping over the elements inside the collection
//        stream.map(x->x*2).sorted().forEach(System.out::println);

        String string="In general, flatMap is useful when you have a function that returns a collection " +
                "(like an array or a list), and you want to flatten the results into a single collection. " +
                "It is commonly used in scenarios involving nested or mapped data structures. The specific" +
                " behavior may vary across programming languages, so it's essential to refer to the documentation" +
                " of the language or library you are using for precise details.";

//        so we have to calculate the number of words in above string
        List<String> stringList= Arrays.asList(string.split(" "));

//        output of the flatmap is always a stream
        int count_of_letters=stringList.stream().flatMap((x)->Arrays.asList(x.split("")).stream()).collect(Collectors.toList()).size();
        System.out.println(count_of_letters);

//        so also create the map of letters and how many time they appeared
        Map<String, Long> map= stringList.stream()
                .flatMap(x->Arrays.asList(x.toLowerCase().split("")).stream()).sorted()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                ;

        System.out.println(map);
    }
}
