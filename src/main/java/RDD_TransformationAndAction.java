import org.apache.hadoop.util.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import java.util.Arrays;
import java.util.Iterator;

public class RDD_TransformationAndAction {
    private SparkConf conf = new SparkConf().setAppName("testingSpark").setMaster("local");
    private JavaSparkContext sc = new JavaSparkContext(conf);
    public static void main(String[] args) {
        RDD_TransformationAndAction tra = new RDD_TransformationAndAction();
        tra.squaringValue();
        tra.flatmapExample();
    }
    public void squaringValue(){
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1,2,3,4));
        JavaRDD<Integer> result = rdd.map((Function<Integer, Integer>) x -> x*x);
        System.out.println(StringUtils.join(".", result.collect()));
    }
    public void flatmapExample(){
        JavaRDD<String> lines = sc.parallelize(Arrays.asList("hello world", "hi"));
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String line) {
                return Arrays.asList(line.split(" ")).iterator();
            }
        });
        words.first(); // returns "hello"
    }
}
