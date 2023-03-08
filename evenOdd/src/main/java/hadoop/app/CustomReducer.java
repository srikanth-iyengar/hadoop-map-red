package hadoop.app;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomReducer extends Reducer<Text, IntWritable, Text, Text> {
	final Text result = new Text();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		HashSet<Integer> st = new HashSet<>();
		values.forEach(num -> st.add(Integer.parseInt(num.toString())));


		result.set(key.toString() + "_NUMBERS");
		context.write(result , new Text(Integer.toString(st.size())));
		result.set(key.toString() + "_COUNT");
		context.write (result , new Text(st.toString()));
	}
}
