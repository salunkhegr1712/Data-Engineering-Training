package SourceCode;

import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.Predicate;

public class FunctionalCoding {
    public static void composeDemo() {
        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add      = (value) -> value + 3;

//        compose used to start execution from RHS so add will evaluate first
//        first add operation take place and then multiply
        Function<Integer, Integer> compposeMultiply = multiply.compose(add);

//        apply is used to run that specific function
        Integer result1 = compposeMultiply.apply(3);
        System.out.println(result1);
    }

    public static void andThenDemo() {

        Function<Long, String> convertToStringFunction = (value) -> Long.toString(value);
        LongFunction<String> convertToStringLong = (value) -> Long.toString(value);
        long l = 500l;

        convertToStringFunction.apply(l);
        convertToStringLong.apply(l);


        ////
        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add      = (value) -> value + 3;

//        add then function make sure execution take place from lhs to rhs
        //multiply function will take place first then add will take place and then next
//        one
        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add)
                .andThen((value) -> value * value);

        Integer result2 = multiplyThenAdd.apply(3);
        System.out.println(result2);
    }

    public static void predicateCompositionDemo() {
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX   = (text) -> text.endsWith("x");

//        this predicate functions are used to create predicates
        Predicate<String> composedAnd = startsWithA.and(endsWithX);

        String input = "A hardworking person must relax";

        Predicate<String> composedOr = startsWithA.or(endsWithX);

//        for predicate (a type of function which returns boolean )
//        you have to use test function in order to run predicate function
        System.out.println("Composed AND : " + composedAnd.test(input));
        System.out.println("Composed OR : " + composedOr.test(input));

    }

    public static void main(String[] args) {
        composeDemo();
        andThenDemo();
        predicateCompositionDemo();

//        lets create few  more functions
    }

}
