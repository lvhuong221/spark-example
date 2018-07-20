import org.apache.hadoop.util.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;

public class RDD_TransformationAndAction {
    SparkConf conf = new SparkConf().setAppName("testingSpark").setMaster("local");
    JavaSparkContext sc = new JavaSparkContext(conf);
    public static void main(String[] args) {
        RDD_TransformationAndAction tra = new RDD_TransformationAndAction();
        tra.squaringValue();
    }
    public void squaringValue(){
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1,2,3,4));
        JavaRDD<Integer> result = rdd.map(new Function<Integer, Integer>() {
            public Integer call(Integer x){ return x*x; }
        });
        System.out.println(StringUtils.join(".", result.collect()));
    }
}
