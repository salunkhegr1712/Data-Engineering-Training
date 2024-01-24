package org.example;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Int;
import scala.Tuple2;

import java.util.*;

public class WordCountSimple {
    static int getWordCount(String string,String word){
        return  1;
    }

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
        System.out.println(lines);


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
//        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
//                .filter(word -> !word.equals("to"));
//        //remove any word that is an article a, an, to, in, the, as, is
//        JavaPairRDD<String,Integer> pairRDD = words.mapToPair(w -> new Tuple2<>(w, 1));
//        JavaPairRDD<String, Integer> counts = pairRDD.reduceByKey((x, y) ->  x + y);

//        counts.collect().forEach(System.out::println);
        //counts.saveAsTextFile("path to file");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}