package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;


class SparkOprationsTest {
    @Test
    public void testUniqueStores() {

        List<String> salesList = Arrays.asList("1", "1", "100.0", "900.0", "20200101");
        SparkSession sparkSession= SparkSession.builder().master("local").getOrCreate();

//        Dataset<Row> dataset= sparkSession.read().option("inferSchema","true").format("D:\\Data Enginnering L1\\Spark\\Main-Session-15-jan-2023\\MyResources\\salary.csv").load();

        List<Person> data= new ArrayList<Person>(){{
            add(new Person(24,"Alpha",400000,"engineer"));
            add(new Person(25, "Beta", 450000, "engineer"));
        }};

        Dataset<Row> personDF = sparkSession.createDataset(data, Encoders.bean(Person.class)).toDF();
//        Dataset<Row> uniqueStoresDf = DailyDiscountCalculator.getStores(salesDf);
        Assert.assertTrue(!personDF.isEmpty());
    }

    @Test
    public void testOne(){
        Assert.assertTrue(true);
    }

    @Test
    public void testTwo(){
        int list[]= new int[100];

        Assert.assertTrue(list.length==100);
    }
    
    
    @BeforeClass
    public static void setUp() {
        SparkSession spark = SparkSession
                .builder()
                .appName("SocgenJava")
                .master("local[*]")
                .getOrCreate();
    }
    @AfterClass
    public static void tearDown() {
//        spark.stop();
    }
}