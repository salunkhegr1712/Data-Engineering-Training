package org.example;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.collection.immutable.Range;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

//SparkContext is entry point for spark programme
// spark driver programme runs executors with the help of the spark context
// for now we normally create spark session and then extract spark context outside it
public class SparkContextInSpark {
    public static void main(String[] args) {

        SparkSession sparkSession=SparkSession.builder().master("local[1]").getOrCreate();

//        lets also set the configs for it
        sparkSession.conf().set("name","ghanshamSparkSession");
        System.out.println(sparkSession.conf().get("name"));


//        now lets extract sparkContext out of sparkSession
        // the configuration of sparkContext and sparkSession will be different
        SparkContext sparkContext=sparkSession.sparkContext();
        System.out.println(sparkContext.appName());

//        here as we are working on java so we can use javaRDD and javaContext

        JavaSparkContext javaSparkContext= new JavaSparkContext(sparkSession.sparkContext());

//        lets create javaRDD now
        JavaRDD<String> javaRDD=javaSparkContext.textFile("Resources\\text.txt");
        JavaRDD data=javaSparkContext.parallelize(Arrays.asList(1,2,3,4,5,6,7,"w3",34));
        data.collect().forEach(System.out::println);
//        javaRDD.map(x->Arrays.asList(x.split(" "))).collect().stream().flatMap(x->x);
//        now lets create RDD resilient distributed dataset with sparkContext


//        rdd.flatMap(x->Arrays.asList(x.split("\n")));
//        System.out.println(rdd.flatMap(x->x.split("")));
//        also add some timeout for sparkSession

        try
        {
            Thread.sleep(5000);
        }catch (Exception e){e.printStackTrace();}

//        it is always good practise to stop spark session
        sparkSession.stop();
    }
}
