package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem {
    public static void main(String[] args) throws IOException {

        Path path= Paths.get("MyResources\\file.txt");
        Stream<String> files=Files.lines(path);
//        List strings=Arrays.asList(files.collect(Collectors.joining(" ")).split(" "));

//        this will return word and how many times it will repeat
//        System.out.println(strings.stream().sorted().collect(Collectors.groupingBy(x->x,Collectors.counting())));

//        System.out.println(strings.stream().flatMap(x->x.toString().charAt(0)).sorted();
//        another way
        Map<Object, Long> data=files.flatMap(x->Arrays.asList(x.split(" ")).stream()).collect(Collectors.groupingBy(x->x,Collectors.counting()));

        System.out.println(data);
    }
}
