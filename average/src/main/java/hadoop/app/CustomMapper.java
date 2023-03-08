package hadoop.app;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

public class CustomMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException {
            try {
                Integer COLUMN = Integer.parseInt(context.getConfiguration().get("arg-2"));

                String fields[] = value.toString().split(",");

                context.write(new Text("numbers"), new DoubleWritable(Double.parseDouble(fields[COLUMN])));
            }
            catch(Exception e) {
            }
    }
}
