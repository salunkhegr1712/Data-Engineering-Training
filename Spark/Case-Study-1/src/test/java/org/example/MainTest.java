package org.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static org.apache.arrow.flatbuf.Type.List;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.regexp_replace;
import static org.junit.Assert.assertEquals;

class MainTest {

    private SparkSession spark;
    @Before
    public void setUp() {
        spark = SparkSession.builder()
                .appName("sparkJob")
                .master("local") // Use local mode for testing
                .getOrCreate();
    }

    @After
    public void tearDown() {
        if (spark != null) {
            spark.stop();
        }
    }

    @Test
    public void TestToConvertStringToNumber() {
        // passing input files
        String sampleSalesCSV = "Resources/sales.csv";
        String sampleReturnsCSV = "Resources/order-returned-data.csv";

        // Execute the Spark job on main data
        String op=Main.main(new String[]{sampleSalesCSV, sampleReturnsCSV});

        // test to see is everything run fine
        assertEquals(op,"Done"); // Change the expected count based on your data

    }

    @org.junit.jupiter.api.Test
    void testMain() {
        TestToConvertStringToNumber();
    }
}