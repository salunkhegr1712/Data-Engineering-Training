package org.example;
import java.util.function.*;

//new functional interfaces in java
/*

    output-> returnvalue, input -> parameter

    Runnable –> This interface only contains the run() method.
    Comparable –> This interface only contains the compareTo() method.
    ActionListener –> This interface only contains the actionPerformed() method.
    Callable –> This interface only contains the call() method.
    Function-> this interface take one input and provide one output <input,output>(one param and one return value)
    Supplier -> this interface not take any input but produce one output (function with no param but one return value)
    Consumer -> this interface take one input and not provide any output (void function with some parameter
    predicate -> this interface take one inout value and return boolean output

    BiFunction-> interface which take 2 inputs and one output
    BiConsumer -> interface which take 2 input with no output
    BiPredicate -> interface which take 2 input and return boolean value
    LongSupplier,DoubleSupplier,IntSUpplier which returns value as per there name

 */

public class BuiltInJavaFunctionalInterfaces {
    public static void main(String[] args) {
//    lets use lambda functions in order to instantiate functional interfaces

//       # Function

//        here Function : a functional interface which is used to create functions with one parameter and one return value
        Function<Integer,String> myfunction= (q)->{
            return String.valueOf(q);
        };
//        in order to awake Function functions we have to use apply function here
        System.out.println(myfunction.apply(100));//100

//        lets create some Function Functional interface functions and play

        Function<Integer, Integer> sumwith10=(num)->{return 10+num;};
        Function<Integer, Integer> multiplywit10=(num)->{return 10*num;};

//        lets see output of
        System.out.println("sumwith10 function appliied on 5 : "+sumwith10.apply(5));//sumwith10 function appliied on 5 : 15
        System.out.println("multiplywith10 function appliied on 5 : "+multiplywit10.apply(5));//multiplywith10 function appliied on 5 : 50


//        those methods also come with two more functions with  which we can create flow of calculations
        // for that we can use compose or and then functions
        // this flow will also return function
        Function<Integer,Integer> pipeline=sumwith10.compose(sumwith10.compose(multiplywit10));
//        execution starts from right to left
//        (5)->5*10->50+10->60+10==>70
        System.out.println("pipeline for 5 :"+pipeline.apply(5));

//        we can also create flow with and then
        Function<Integer,Integer> pipe2=sumwith10.andThen(multiplywit10).andThen(multiplywit10).andThen(sumwith10);
//        (5)->5+10->15*10->150*10->1500+10==>1510
        System.out.println("pipe2 for 5 : "+pipe2.apply(5));

//        Bi fucntions
        // function take two interger and return long
        BiFunction<Integer,Integer,Long> mul=(a,b)-> (long) (a*b);
        System.out.println(mul.apply(100000,14542));//1454200000


//    # Supplier
        // <Datatype in supplier signify the data type of return value
        Supplier<String> mysupplier = () -> "hello world";
//    There are some variations on Supplier available in java like IntSupplier, DoubleSupplier, and LongSupplier

        LongSupplier longSupplier = () -> new Long(100);
        DoubleSupplier doubleSupplier = () -> 100;
        IntSupplier intSupplier = () -> 100;

//        to run supplier we have to use .get function
        System.out.println(mysupplier.get()+" ; "+longSupplier.getAsLong()+" : "+ doubleSupplier.getAsDouble()+" : "
            +intSupplier.getAsInt()
        );
        //hello world ; 100 : 100.0 : 100


//        #Consumer
        Consumer<Integer> myConsumer=(a)->{
            System.out.println("internal value "+a);
        };

//        we need to invoke accept function to do action in consumer function
        myConsumer.accept(100);
//        internal value 100

        BiConsumer<Integer,String> biConsumer=(a,b)->{
            System.out.println(a+" "+b);
        };
        biConsumer.accept(100,"hello world");

//        #predicate functions
        Predicate<Integer> greaterThan100=(number)->{
            return  number>100;
        };
        System.out.println(greaterThan100.test(150));//true

//        we can create negate function from predicate functions
        System.out.println(greaterThan100.negate().test(150));//false
//        we have to run test function to invoke biConsumer functions

        BiPredicate<Integer,Integer> firstGreaterThanSecond=(a,b)->{
            return a>b;
        };
        System.out.println(firstGreaterThanSecond.test(100,67));//true

    }
}

