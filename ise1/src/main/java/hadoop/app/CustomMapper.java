package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/*
 * Find number of unique zip codes in each state of ev charging dataset
 */
public class CustomMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static  int ZIP_COL = 4, STATE_COL = 3;

    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {
        try { 
            String fields[] = value.toString().split(",");
            context.write(new Text(fields[STATE_COL]), new IntWritable(Integer.parseInt(fields[ZIP_COL])));
        }
        catch(Exception e) {
        }
    }
}
