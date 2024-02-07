package org.example;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

public class SchemaBasedDataset {
    public static void main(String[] args) {

        StructType schema =DataTypes.createStructType(new StructField[] {
                DataTypes.createStructField("item_id",  DataTypes.IntegerType, true),
                DataTypes.createStructField("item_qty", DataTypes.IntegerType, true),
                DataTypes.createStructField("unit_price", DataTypes.FloatType, true),
                DataTypes.createStructField("total_amount", DataTypes.IntegerType, true),
                DataTypes.createStructField("date_of_sale", DataTypes.DateType, true)
        });
    }
}
