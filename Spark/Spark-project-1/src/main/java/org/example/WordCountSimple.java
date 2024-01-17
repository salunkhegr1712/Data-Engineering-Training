package org.example;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;

public class WordCountSimple {
    public static void main(String[] args) {

        String url = "file:///C:\\Trainings\\file.txt";
        String winutilPath = "C:\\Software\\winutils\\"; //\\bin\\winutils.exe"; //bin\\winutils.exe";

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            System.out.println("Detected windows");
            System.setProperty("hadoop.home.dir", winutilPath);
            System.setProperty("HADOOP_HOME", winutilPath);
        }

        SparkSession spark = SparkSession
                .builder()
                .appName("SocgenJava")
                .master("local[*]")
                .getOrCreate();

        JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());
        JavaRDD<String> lines = sparkContext.textFile(url);
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .filter(word -> !word.equals("to"));
        //remove any word that is an article a, an, to, in, the, as, is
        JavaPairRDD<String,Integer> pairRDD = words.mapToPair(w -> new Tuple2<>(w, 1));
        JavaPairRDD<String, Integer> counts = pairRDD.reduceByKey((x, y) ->  x + y);

        counts.collect().forEach(System.out::println);
        //counts.saveAsTextFile("path to file");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}