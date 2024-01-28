package org.example;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class City {

    // Initializing the properties
    // of the city class
    private String name;
    private double temperature;

    // Parameterized constructor to
    // initialize the city class
    public City(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    // Getters to get the name and
    // temperature
    public String getName() {
        return name;
    }

    public Double getTemperature() {
        return temperature;
    }

    // Overriding the toString method
    // to return the name and temperature
    @Override
    public String toString() {
        return name + " --> " + temperature;
    }
}

public class StreamCollectorsInJava {
    public static void main(String[] args) {

        List<City> cities = new ArrayList<>();
        cities.add(new City("New Delhi", 33.5));
        cities.add(new City("Mexico", 14));
        cities.add(new City("New York", 13));
        cities.add(new City("Dubai", 43));
        cities.add(new City("London", 15));
        cities.add(new City("Alaska", 1));
        cities.add(new City("Kolkata", 30));
        cities.add(new City("Sydney", 11));
//        cities.add(new City("Mexico", 14));
        cities.add(new City("Dubai", 43));

//        collect function is one of the function which used in streams
        System.out.println(cities);

//        lets get cities whose n temperature >10
//        List<String> mylist= Collections.singletonList(cities.stream()
//                .filter(temp -> temp.getTemperature() > 15)
//                .map(f -> f.getName())
//                .collect(Collectors.joining("--")));

//        query 2 -> convert vity name into list
//        List<String> mylist=cities.stream()
//                .filter(temp -> temp.getTemperature() > 15)
//                .map(f -> f.getName())
//                .collect(Collectors.toList());

//        query 3- get map
        Map mymap = cities.stream()
                .filter(temp -> temp.getTemperature() > 10)
                .collect(Collectors.toMap(City::getName, City::getTemperature, (key, value) -> key));
        System.out.println(mymap);

//        query 4 - group by with city name
        System.out.println(cities.stream()
                .filter(temp -> temp.getTemperature() > 10)
                .collect(Collectors.groupingBy(City::getName))
        );

        System.out.println(cities.stream().collect(Collectors.
                groupingBy(
                        City::getName,
                        Collectors.collectingAndThen(Collectors.counting(),
                                f -> f.intValue()))));

        System.out.println(cities.stream()
                .collect(Collectors.groupingBy(City::getName,Collectors.mapping(City::getTemperature,Collectors.toList()))));
    }
}
