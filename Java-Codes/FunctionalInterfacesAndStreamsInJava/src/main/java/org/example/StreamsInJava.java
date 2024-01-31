package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamsInJava {

    static void printStream(Stream a){
        a.forEach((x)->{
            System.out.println(x);
        });
    }
    public static void main(String[] args) throws IOException {

// ======================================| ways to create streams in java |============================================
//        creating stream with collection
        List mylist= Arrays.asList("a",1,2,4,5.4,"dasd");

//        creating stream with array
        String[] arr = new String[]{"a", "b", "c"};
        Stream arrayStram= Arrays.stream(arr);

//        getting stream from list
        Stream myStream=mylist.stream();

//        creating stream with Stream.of fuinction
        // skip function used to ignore starting 2 numbers
//        sorted function used in order to convert stream into sorted once
        Stream anotherStream= Stream.of("1","bdas",'c',"d","f",11,11.34,true).skip(2).sorted();

        Stream mystream=Stream.builder().add("Ghansham").add("Rajaram").add("salunkhe").build();
        Long m= mystream.count(); // count function used to count elements inside the stream

//        generate empty stram
        Stream stream=Stream.empty();
//        printStream(mystream);

//        always use limit for iterate and generate
//        in gemerate stream we have to pass supplier function to create streams but you shpuld also put
//        limit else it will become infinite loop
        Stream stream1=Stream.generate(()->{return "hello world";}).limit(10);
//        printStream(stream1);

//        create stream with iterate seed it starting point
        Stream stream2=Stream.iterate(10,(x)->{return  x+100;}).limit(10);
//        printStream(stream2);


//        you can also create intStream and
        IntStream intStream=IntStream.range(1,20);
        LongStream longStream=LongStream.rangeClosed(1,3);
//        printStream((Stream) intStream);

//        randome to create random values
//        random is very much helpfull in many conditions
        Random val= new Random();
        val.ints().limit(10).forEach((x)->{
//            System.out.println(x);
        });
        val.doubles().limit(10).forEach((x)->{
//            System.out.println(x);
        });

//        we can also create stream of files
//        with files and path we can also able to read files and also will be able create streams
        Path path= Paths.get("MyResources\\file.txt");
        Stream streamOfLines=Files.lines(path);


// ======================================| operations on stream |============================================

        // FIlter
//        just like map function filter function also return stream of elements
        Stream<String> stream3=streamOfLines.filter((z)->{return z.toString().startsWith("ghansham");});
//        printStream(stream3);

//        lets create random int stream
        List<Integer> stream4=Arrays.asList(11,234,43,124,637,6524,34647,2143,32,43,5745,23534,223,423,235);

//        filter it
        Stream stream5=stream4.stream().filter((x)->{return x>=500;});
        stream5.forEach((x)->{System.out.println(x);});

//        map function is used for transformation of stream
        Stream outputOfMap=anotherStream.map((x)->{
            return "value : "+x;
        });



    }
}
