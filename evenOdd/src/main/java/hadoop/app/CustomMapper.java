package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class CustomMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {

            int COLUMN_NO = Integer.parseInt(context.getConfiguration().get("args-2"));

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
