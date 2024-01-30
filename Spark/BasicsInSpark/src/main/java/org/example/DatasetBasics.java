package org.example;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.*;
import scala.Function1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatasetBasics {
    public static void main(String[] args) throws AnalysisException {

//        inside this code i will use dataframe
        SparkSession mySparkSession=SparkSession.builder().master("local[1]").getOrCreate();

//        loading dataset as the dataset of employee class using employee class
        Dataset<Employee> myDataset=mySparkSession.read()
                .option("header","true")
                .option("delimiter",",")
                .option("inferSchema", "true")
                .csv("MyResources\\MyData.csv").as(Encoders.bean(Employee.class));

        Dataset<Employee> anotherDataframe =myDataset.as(Encoders.bean(Employee.class));

//        also print the schema which is Employee.class
        System.out.println(Employee.class);
        myDataset.show();
//        myDataset.printSchema();

//        Dataset<Row> df= myDataset.filter("salary>75000").select("employeeID","lastName","salary").withColumnRenamed("lastName","lname");

//        df.show();

//        myDataset.createTempView("mytable");
//        mySparkSession.sql("select * from mytable where mytable.isManager=true").show();
//        System.out.println("zfbhjjhdsfhbsdhk");


//        now lets convert dataframe into some another kind of dataframe


//        JavaSparkContext javaSparkContext=new JavaSparkContext(mySparkSession.sparkContext());
//        JavaRDD<String> lines=javaSparkContext.textFile("MyResources\\MyData.csv");
//
//        JavaRDD<Employee> employees= lines.map(new Function<String, Employee>() {
//            @Override
//            public Employee call(String s) throws Exception {
//                List<String> myStrings= Arrays.asList(s.split(","));
//
//                Employee employee=new Employee(
//                        Integer.parseInt(myStrings.get(0)),
//                        myStrings.get(1),
//                        myStrings.get(2),
//                        myStrings.get(3),
//                        Integer.parseInt(myStrings.get(4)),
//                        myStrings.get(5),
//                        myStrings.get(6),
//                        Boolean.parseBoolean(myStrings.get(7)),
//                        Integer.parseInt(myStrings.get(8)),
//                        myStrings.get(9)
//                );
//
//                return employee;
//            }
//        });

//        employees.foreach(System.out::println);

//        lets map each row to list of objects
        try {
            Thread.sleep(10000);
        }catch (Exception a){
            a.printStackTrace();
        }

        mySparkSession.stop();
    }
}
