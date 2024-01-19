package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.collection.Seq;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","C://Software//winutils");
//        lets create a spark session
        SparkSession mysession = SparkSession.builder().appName("MySparkSession").master("local[1]").getOrCreate();
        System.out.println("version of spark : " + mysession.version());

//        creating data frame from the csv file
        Dataset<Row> mydataframe= mysession.read().csv("MyResources//MyData.csv");
//        mydataframe.show();

        List<String> oldColumns=Arrays.asList(mydataframe.columns());
        System.out.println(oldColumns);
        Row myRow;
        List<String> newColumns= Arrays.asList(String.valueOf(mydataframe.collectAsList().get(0).toSeq()));
//        mydataframe.to;
//        lets try to use new and more functions with spark language
        mydataframe.show();
        System.out.println();
        System.out.println(mydataframe.columns());
        System.out.println(mydataframe.describe());

//        now create some additional functions over the dataframe
//        Dataset<Row> subset=;


    }
}
