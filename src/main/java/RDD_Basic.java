import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;

public class RDD_Basic {
    public static void main(String[] args) {
        JavaSparkContext sc = null;
        //Tạo RDD, cần dữ liệu có trên máy
        JavaRDD<String> line1 = sc.parallelize(Arrays.asList("panda", "i like panda"));

        //Tạo RDD từ file có sẵn
        JavaRDD<String> line2 = sc.textFile("/path/to/README.md");

        //Biến đổi
        //Lọc từ với filter(). inputRDD không thay đổi
        //filter() trả lại 1 RDD mới
        JavaRDD<String> inputRDD = sc.textFile("log.txt");
        JavaRDD<String> errorsRDD = inputRDD.filter(
                (Function<String, Boolean>) x -> x.contains("error")
        );
        //Ghép 2 RDD lại
        JavaRDD<String> warningRDD = inputRDD.filter(
                (Function<String, Boolean>) x -> {return x.contains("warning");});
        JavaRDD<String> badLineRDD = errorsRDD.union(warningRDD);

        //Action
        System.out.println(badLineRDD.count()+" concerning lines");
        System.out.println("10 example: ");
        for(String line: badLineRDD.take(10)){
            System.out.println(line);
        }




    }
    //Truyền function sang Spark
    public void passingFunctionToSpark(JavaRDD<String> lines){
        JavaRDD<String> errors = lines.filter(new Function<String, Boolean>() {
            public Boolean call(String x) { return x.contains("error"); }
        });
    }
}
