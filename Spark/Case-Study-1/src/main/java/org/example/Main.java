package org.example;

import org.apache.spark.sql.*;

import java.util.List;

import static org.apache.spark.sql.functions.*;

public class Main {
    public static String main(String[] args) {

        String sales_path;
        String returns_path;
        if (args.length==2){
            sales_path=args[0];
            returns_path=args[1];
        }
        else{
            sales_path="Resources/sales.csv";
            returns_path="Resources/order-returned-data.csv";
        }
//        input files


        SparkSession mysession= SparkSession.builder().master("local").appName("SparkJob").getOrCreate();

        mysession.sparkContext().setLogLevel("WARN");

//        now lets read 2 csv and then we have to go
        Dataset<Row> order_returned_data=mysession.read().option("header", "true").option("dateFormat", "MM/dd/yyyy")
                .option("inferSchema","true")
                .csv(returns_path);

        Dataset<Row> sales_data=mysession.read().option("header", "true").option("inferSchema","true")
                .csv(sales_path);

//        order_returned_data.printSchema();
//        sales_data.printSchema();
        // preprocess data WE dont have to preprocess the order returned data we have to use sales data only

//        now lets get order which not returned
        Dataset<Row> sales_data_for_orders_not_returned=sales_data.join(order_returned_data,
                sales_data.col("Order ID").equalTo(order_returned_data.col("Order ID")),
                "left_anti");

//        also we have to convert columns into double
        Dataset<Row>  sales_data_for_orders_not_returned1= sales_data_for_orders_not_returned.withColumn("Sales", regexp_replace(col("Sales"), "[^0-9.]+", "").cast("double"));
//        sales_data_for_orders_not_returned1.printSchema();

        Dataset<Row> sales_data_preprocessed = sales_data_for_orders_not_returned1.withColumn("Profit", regexp_replace(col("Profit"), "[^0-9.]+", "").cast("double"));
//        sales_data_for_orders_not_returned2.printSchema();

        Column orderDateColumn=sales_data_preprocessed.col("Order Date");
        Column data= functions.split(orderDateColumn,"/") ;

        // Extract year and month from the array
        Column yearColumn = data.getItem(2).cast("int").alias("Year");
        Column monthColumn = data.getItem(0).cast("int").alias("Month");
//        now lets add those columns into dataset

        sales_data_preprocessed=sales_data_preprocessed.withColumn("Year",yearColumn);
        sales_data_preprocessed=sales_data_preprocessed.withColumn("Month",monthColumn);

        sales_data_preprocessed.printSchema();


        Dataset<Row> output = sales_data_preprocessed.groupBy("Year", "Month", "Category", "Sub-Category")
                .agg(sum("Quantity").cast("int")
                        .alias("Total Quantity Sold"), sum("Profit")
                        .cast("int").alias("Total Profit"));


//        now lets try to partition data

        output.write().mode("Overwrite").partitionBy("Year","Month").csv("Outputs/");
//        output.show(100);


        try{Thread.sleep(10000);}catch (Exception e){e.printStackTrace();}
        mysession.stop();

        return "Done";
    }
}
