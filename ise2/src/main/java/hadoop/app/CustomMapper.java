package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;


/*
 * Find average of EV Level1 EVSE Num for every EV network
 */
public class CustomMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private final static int NETWORK=8,EV_LEVEL=6;
    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {
        
        try {
            String fields[] = value.toString().split(",");
            context.write(new Text(fields[NETWORK]), new DoubleWritable(Double.parseDouble(fields[EV_LEVEL])));
        }
        catch(Exception e) {
        }
    }
}
