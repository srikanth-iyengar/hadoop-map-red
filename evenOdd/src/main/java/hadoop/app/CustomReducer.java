package hadoop.app;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		HashSet<Integer> st = new HashSet<>();
		int count = 0;
        for (IntWritable value : values) {
            count += value.get();
			st.add(value.get());
        }
        context.write(new Text("ANSWER"), new IntWritable(st.size()));
	}
}
