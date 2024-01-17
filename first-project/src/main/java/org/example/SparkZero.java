package org.example;

import org.apache.spark.sql.SparkSession;

public class SparkZero {
    public static void main(String[] args) {

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

        spark.stop();

    }
}