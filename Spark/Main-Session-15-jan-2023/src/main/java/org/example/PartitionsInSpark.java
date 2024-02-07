package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class PartitionsInSpark {
    public static void main(String[] args) {

        SparkSession session=SparkSession.builder().master("local").getOrCreate();
//        lets read  a csv and create db
        Dataset<Row> dataset=session.read().option("header","true").option("inferSchema","true").csv("MyResources/sales_1.csv");
        dataset.show();

//        now lets partiion of data using unit_price
        dataset.write().mode("Overwrite").partitionBy("date_of_sale").csv("Outputs/partitionByUnitPrice");

    }
}
