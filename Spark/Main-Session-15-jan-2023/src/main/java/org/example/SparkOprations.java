package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import shaded.parquet.org.apache.thrift.transport.THttpClient;

import java.util.Arrays;
import java.util.List;

public class SparkOprations {

    public static Dataset<Row> readSales(SparkSession spark, String path) {


        StructType schema = DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("item_id", DataTypes.IntegerType, true),
                DataTypes.createStructField("item_qty", DataTypes.IntegerType, true),
                DataTypes.createStructField("unit_price", DataTypes.FloatType, true),
                DataTypes.createStructField("total_amount", DataTypes.IntegerType, true),
                DataTypes.createStructField("date_of_sale", DataTypes.DateType, true)
        });

        Dataset<Row> productDf = spark.read()
                .schema(schema)
                .option("header", "true")
                .csv(path);
        return productDf;
    }


    public static void sparkOperations(SparkSession spark) {
        String productUrl;
        String salesUrl;


        Dataset<Row> productDf = spark.read()
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("MyResources/product_meta.csv");

        //productDf.show();

        Dataset<Row> salesDf = spark.read()
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("MyResources/sales_1.csv");

        //salesDf.show();

        Dataset<Row> sales2Df = readSales(spark, "MyResources/sales_2.csv");
        //sales2Df.show();
        //sales2Df.printSchema();
        Dataset<Row> castedDf = salesDf
                .withColumn("date_casted", salesDf.col("date_of_sale").cast(DataTypes.DateType))
                .withColumn("unit_price_casted", salesDf.col("date_of_sale").cast(DataTypes.FloatType));
        salesDf = castedDf
                .drop("date_of_sale").withColumnRenamed("date_casted", "date_of_sale")
                .drop("unit_price").withColumnRenamed("unit_price_casted", "unit_price");
        //salesDf.printSchema();

        Dataset<Row> allSalesDf = salesDf.select("item_id", "item_qty", "unit_price", "total_amount", "date_of_sale")
                .union(sales2Df.select("item_id", "item_qty", "unit_price", "total_amount", "date_of_sale"));
        allSalesDf.show();
        Dataset<Row> joinedDf = salesDf.join(productDf,
                salesDf.col("item_id").equalTo(productDf.col("item_id")),
                "inner");

        //with SQL

        salesDf.createOrReplaceTempView("sales");
        productDf.createOrReplaceTempView("product");

        Dataset<Row> temp = spark.sql("select * from sales a join product b " +
                "on a.item_id = b.item_id");
    }

    public static void main(String[] args) {


        SparkSession spark = SparkSession
                .builder()
                .appName("SocgenJava")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> productDf = spark.read()
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("MyResources/product_meta.csv");

        //productDf.show();
        Dataset<Row> salesDf = spark.read()
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("MyResources/sales_1.csv");

        //salesDf.show();
        Dataset<Row> sales2Df = readSales(spark, "MyResources/sales_2.csv");

        productDf.show();salesDf.show();sales2Df.show();


        //sales2Df.show();
        //sales2Df.printSchema();
        Dataset<Row> castedDf = salesDf
                .withColumn("date_casted", salesDf.col("date_of_sale").cast(DataTypes.DateType))
                .withColumn("unit_price_casted", salesDf.col("date_of_sale").cast(DataTypes.FloatType));

        salesDf = castedDf
                .drop("date_of_sale").withColumnRenamed("date_casted", "date_of_sale")
                .drop("unit_price").withColumnRenamed("unit_price_casted", "unit_price");
        //salesDf.printSchema();
//
        castedDf.show();


        Dataset<Row> joined1= salesDf.join(productDf,salesDf.col("item_id").equalTo(productDf.col("item_id")));
        Dataset<Row> joined2= sales2Df.join(productDf,sales2Df.col("item_id").equalTo(productDf.col("item_id")));


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        joined1=joined1.select("product_name","item_name","unit_price","item_qty","total_amount");
        joined2=joined2.select("product_name","item_name","unit_price","item_qty","total_amount");

        joined1.join(joined2,joined1.col("item_name").equalTo(joined2.col("item_name"))).show();

//        List<String> products= Arrays.asList(productDf.col("item_name"));

//        Dataset<Row> allSalesDf = salesDf.select("item_id", "item_qty", "unit_price", "total_amount", "date_of_sale")
//                .union(sales2Df.select("item_id", "item_qty", "unit_price", "total_amount", "date_of_sale"));
//        allSalesDf.show();
//        Dataset<Row> joinedDf = salesDf.join(productDf,
//                salesDf.col("item_id").equalTo(productDf.col("item_id")),
//                "inner");
//
//        //with SQL
//
//        salesDf.createOrReplaceTempView("sales");
//        productDf.createOrReplaceTempView("product");
//
//        Dataset<Row> temp = spark.sql("select * from sales a join product b " +
//                "on a.item_id = b.item_id");
//        //temp.explain();
//        joinedDf.explain();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

