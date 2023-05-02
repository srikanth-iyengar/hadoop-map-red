package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;

import org.apache.hadoop.io.Text;

public class CustomMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final static int CITY=1,MAC=2,TARGET_PORT=3;

    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {

            try {
                String fields[] = value.toString().split(",");
                context.write(new Text(fields[CITY]), new Text(fields[TARGET_PORT]));
            }
            catch(Exception e) {
            }
    }
}
