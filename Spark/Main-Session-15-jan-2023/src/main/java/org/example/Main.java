package org.example;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        String url = "MyResources\\MyTextdata.txt";
        SparkSession mysession= SparkSession.builder().master("local[1]").appName("SparkSession").getOrCreate();


        JavaSparkContext sparkContext = new JavaSparkContext(mysession.sparkContext());
        JavaRDD<String> lines = sparkContext.textFile(url);
        System.out.println(lines);
        System.out.println("------------------------------------------------------- Output according to My code -------------------------------------------------------");

        List<String> listOfLines= lines.collect();
        List<String> allWords=new LinkedList<>();

        for (int i = 0; i < listOfLines.size(); i++) {
//            System.out.println("line "+(i+1)+" : "+listOfLines.get(i));
            List<String>buff=new ArrayList<>(Arrays.asList(listOfLines.get(i).split(" ")));
            System.out.println("words are : "+buff);
            allWords.addAll(buff);
        }

        Map<String, Integer> map= new TreeMap<String,Integer>();
        for (int i = 0; i < allWords.size(); i++) {
            String word=allWords.get(i);
            if(map.containsKey(word)){
                map.replace(word,map.get(word)+1);
            }
            else {
                map.put(word,1);
            }
        }

        List<String> keys=new ArrayList<>(map.keySet());
        List<Integer> values= new ArrayList<>(map.values());

        for (int i = 0; i < keys.size(); i++) {
            System.out.println("( "+keys.get(i)+" : "+values.get(i)+" )");
        }

        System.out.println("------------------------------------------------------- Output according to instructor code -------------------------------------------------------");
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .filter(word -> !word.equals("to"));
        //remove any word that is an article a, an, to, in, the, as, is
        JavaPairRDD<String,Integer> pairRDD = words.mapToPair(w -> new Tuple2<>(w, 1));
        JavaPairRDD<String, Integer> counts = pairRDD.reduceByKey((x, y) ->  x + y);

        counts.collect().forEach(System.out::println);
//        counts.saveAsTextFile("myfile.txt");
//        counts.saveAsTextFile();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mysession.stop();
    }
}