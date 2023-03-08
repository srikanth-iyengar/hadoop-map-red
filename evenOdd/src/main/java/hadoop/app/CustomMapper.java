package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class CustomMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final int COLUMN_NO = 6;
    private final Text weatherCondition = new Text();
    private final IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {
            try 
            {
                String[] fields = value.toString().split(",");
                Integer x = Integer.parseInt(fields[COLUMN_NO]);
                if(x != null) {
                    context.write(new Text( x % 2 == 0 ? "EVEN" : "ODD" ), new IntWritable(x));
                }
            }
            catch(Exception e) {
            }
    }
}
