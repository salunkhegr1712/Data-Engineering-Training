package org.example;

import org.apache.spark.SparkContext;
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
        SparkContext sparkContext1=new SparkContext();
        System.out.println(sparkContext.appName());


//        now lets create RDD resilient distributed dataset with sparkContext

        RDD<String> rdd=sparkContext.textFile("Resources\\text.txt",1);
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
