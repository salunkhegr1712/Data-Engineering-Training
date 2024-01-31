package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

public class CreateSparkUsingSparkConf {

    public static void main(String[] args) {

        SparkConf sparkConf= new SparkConf().setAppName("sparkSession").set("spark.master","local[1]");

//        lets create a spark session on top of sparkConf
        SparkContext sparkContext=new SparkContext(sparkConf);
        System.out.println(sparkContext.conf());
//        lets also close the system
        sparkContext.stop();
    }
}
