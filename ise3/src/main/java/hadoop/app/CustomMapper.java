package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class CustomMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final int ZIP=4, LEVEL1=5, LEVEL2=6, FAST_DC=7;
    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {
            try {
                String fields[] = value.toString().split(",");
                Double.parseDouble(fields[LEVEL1]);
                Double.parseDouble(fields[LEVEL2]);
                Double.parseDouble(fields[FAST_DC]);
                context.write(new Text(fields[ZIP]), new Text(fields[LEVEL1] + "," + fields[LEVEL2] + "," + fields[FAST_DC]));
            }
            catch(Exception e) {
            }
    }
}
