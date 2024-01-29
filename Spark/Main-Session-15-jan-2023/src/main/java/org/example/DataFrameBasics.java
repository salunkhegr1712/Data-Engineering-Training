package org.example;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import scala.Function1;

import java.util.Arrays;



public class DataFrameBasics {
    static String url = "MyResources\\salary.csv";


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
        df.filter("age > 25").select("name","designation").show();
    }

    public static void datasetVersion(SparkSession spark) {

        Dataset<Row> df = spark.read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv(url);
        df.show();
        df.printSchema();
        df.filter("age > 25").show();

        df.selectExpr("name","age","salary","(salary/80) as Salary_in_Dollars").show();
//        encode data into person class
        Dataset<Person> x = df.as(Encoders.bean(Person.class));
        x.show();

//        we can filter with some function also
        Dataset<Person> filteredDs = x.filter(new FilterFunction<Person>() {
            @Override
            public boolean call(Person value) throws Exception {
                return value.getAge() < 25;
            }
        });
        x.show();

        Dataset<Person> adult = x.filter((FilterFunction<Person>) p -> p.isAdult());

    }

    public static void sqlVersion(SparkSession spark) throws AnalysisException {

        Dataset<Row> df = spark.read()
                .option("inferSchema", "true")
                .option("header", "true").csv(url);
        df.createOrReplaceTempView("emptable");
        spark.sql("select * from emptable where age > 10").show();
    }

    public static void main(String[] args) {

        SparkSession spark = SparkSession
                .builder()
                .appName("SocgenJava")
                .master("local[*]")
                .getOrCreate();

//        rddVersion(spark);
        datasetVersion(spark);
//        dataframeVersion(spark);

        try {
           sqlVersion(spark);
        } catch (AnalysisException e) {
            e.printStackTrace();
        }
        try{
            Thread.sleep(10000);
        }catch (Exception r){r.printStackTrace();}
//
    }
}