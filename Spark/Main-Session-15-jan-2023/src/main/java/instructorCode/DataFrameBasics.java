package instructorCode;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import scala.Function1;

import java.util.Arrays;


public class DataFrameBasics {
    static String url = "file:///C:\\Training\\sockgen_spark_java\\salary.csv";


    public static void rddVersion(SparkSession spark) {
        JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());
        JavaRDD<String> lines = sparkContext.textFile(url)
                .filter(line -> !line.startsWith("name")); //to remove header

        JavaRDD<Person> people = lines.map(new Function<String, Person>() {
            @Override
            public Person call(String s) throws Exception {
                String[] values = s.split(",");
                String name = values[0];
                Integer age = Integer.parseInt(values[1]);
                Integer salary = Integer.parseInt(values[2]);
                String designation = values[3];
                Person p = new Person();
                p.setAge(age);
                p.setName(name);
                p.setSalary(salary);
                p.setDesignation(designation);
                return p;
            }
        });

        people.filter((Function<Person, Boolean>) person -> person.getAge() > 25)
                .collect().forEach(System.out::println);

    }

    public static void dataframeVersion(SparkSession spark) {

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv(url);

        df.show();
        df.printSchema();
        df.filter("age > 25").select("name", "designation").show();
    }

    public static void datasetVersion(SparkSession spark) {

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv(url);
//        df.show();
//        df.printSchema();
//        df.filter("age > 25").show();

        Dataset<Person> x = df.as(Encoders.bean(Person.class));
        Dataset<Person> filteredDs = x.filter((FilterFunction<Person>)
                value -> value.getAge() < 25);
        filteredDs.show();

        Dataset<Person> adult = x.filter((FilterFunction<Person>) p -> p.isAdult());
        adult.show();

    }

    public static void sqlVersion(SparkSession spark) throws AnalysisException {

        Dataset<Row> df = spark.read()
                .option("inferSchema", "true")
                .option("header", "true").csv(url);
        df.createOrReplaceTempView("emptable");
        spark.sql("select * from emptable where age > 25").show();
    }

    public static void main(String[] args) {

        SparkSession spark = SparkSession
                .builder()
                .appName("SocgenJava")
                .master("local[*]")
                .getOrCreate();

//        rddVersion(spark);
//        dataframeVersion(spark);
//        datasetVersion(spark);
        try {
           sqlVersion(spark);
        } catch (AnalysisException e) {
            e.printStackTrace();
        }
    }
}
