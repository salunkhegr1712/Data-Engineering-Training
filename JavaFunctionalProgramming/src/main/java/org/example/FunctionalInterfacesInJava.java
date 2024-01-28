package org.example;

//a interface  which has only one abstract function is called as functional Interface
// you also have to annotate that interface with @FunctionalInterface
// ex : runnable is thread creation interface and it has only one function run and it is a functional interface

@FunctionalInterface
interface MyFunctionalInterface{
    public int functionToReturnBiggestValueAmongParameters();
//    public int function2();
}
public class FunctionalInterfacesInJava {
    public static void main(String[] args) {

//        before java 8 we used to create functional interface with this way
        Thread mythread= new Thread(new Runnable() {
            @Override
            public void run() {
                return;
            }
        });

//        start this thread
        mythread.start();

//        but after java 8 we can use specific type of notation and it is good
//        this way of creating functions are called as lambda expressions
        Thread mythread1=new Thread(()->{
            return;
        });
        mythread1.start();


//        now lets use our own created functional interface
//
        MyFunctionalInterface s= ()->{
            return 0;
        };

        System.out.println(s.functionToReturnBiggestValueAmongParameters());
        //0
    }
}
