package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Movies implements Comparable<Movies> {
    public String name;

    public int releaseDate;

    public double rating;

    public String getName() {
        return name;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public Movies(String name, int releaseDate, double rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }


    //        say we have to sort according to release year
    @Override
    public int compareTo(Movies o) {
//            this will return a number on which sorting is based
//            this function will triggered from body of one object and and one object is passed as parameter
        return this.releaseDate-o.getReleaseDate();
    };

    @Override
    public String toString() {
        return "{ "+"movies : "+getName()+" ; rating : "+getRating()+" ; release_year : "+getReleaseDate()+" }";
    }

//    this function is comparator which means we have to pass it explicitly to collections.sort
    public int comparatorFunction(Movies o){
        if (o.getRating()>this.getRating())
            return 1;
        else if (o.getRating()==this.getRating()) {
            return 0;
        }
        else
            return -1;
    }
}
//there are two ways are there to compare two objects
public class ComparableAndComparatorInJava {

    //  1)  create a class implementing comparable interface

//    this compareTo function come to play when we use Collection.sort function on of movie class

    public static void main(String[] args) {

        List<Movies> listOfMovies=new ArrayList<>();

//        lets compare two movies object
        listOfMovies.add(new Movies("tarzon",2001,8.3));
        listOfMovies.add(new Movies("rooh",2021,6.3));
        listOfMovies.add(new Movies("batman",2007,9.0));
        listOfMovies.add(new Movies("got",2018,8.9));
        listOfMovies.add(new Movies("wonderwomen",2011,7.3));
        listOfMovies.add(new Movies("bumblebee",2023,6.5));

        System.out.println("-----------------------------| starting list |-----------------------------");
        for (Movies a:listOfMovies
             ) {
            System.out.println(a);
        }
//        lets sort the arraylist
        // in this case default comparable function inside the class will invoked
        Collections.sort(listOfMovies);
        System.out.println("-----------------------------| sort according to release year main comparable function |-----------------------------");
        for (Movies a:listOfMovies
        ) {
            System.out.println(a);
        }
//        { movies : tarzon ; rating : 8.3 release_year : 2001 }
//        { movies : batman ; rating : 9.0 release_year : 2007 }
//        { movies : wonderwomen ; rating : 7.3 release_year : 2011 }
//        { movies : got ; rating : 8.9 release_year : 2018 }
//        { movies : rooh ; rating : 6.3 release_year : 2021 }
//        { movies : bumblebee ; rating : 6.5 release_year : 2023 }

        Collections.sort(listOfMovies,Movies::comparatorFunction);

        System.out.println("-----------------------------| sort according to movie rating with comparator function |-----------------------------");
        for (Movies a:listOfMovies
        ) {
            System.out.println(a);
        }
    }


}
