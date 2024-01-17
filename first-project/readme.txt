get hadoop.dll and win-utils.exe from https://github.com/steveloughran/winutils/tree/master/hadoop-3.0.0/bin

you should have
hadoop.dll in windows/system32
have folder C:/Software/winutils/bin put win-utils.exe file



--------> add configuration in build.gradle

plugins {
    id 'java'
    id 'idea'
}

group 'com.bdec.training.spark'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.google.guava:guava:29.0-jre'
    testImplementation 'junit:junit:4.13'
    implementation 'org.apache.spark:spark-sql_2.12:3.0.0'
    implementation 'org.apache.spark:spark-mllib_2.12:3.0.0'
    //testImplementation 'org.junit.jupiter:junit-jupiter-engine:5.8.2'
}

test {
    useJUnit()
}

tasks.withType(JavaCompile) {
    options.release = 8
}

------> then create a class with code

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

---> now create a run configuration for that class

select jdk version
-cp add .main file
then from modify options add --> add vm configuration then add
==> --add-opens java.base/java.nio=ALL-UNNAMED --add-exports=java.base/sun.nio.ch=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.reflect=ALL-UNNAMED  --add-opens=java.base/java.io=ALL-UNNAMED --add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED

then select class SparkZero in class and save it and run it
