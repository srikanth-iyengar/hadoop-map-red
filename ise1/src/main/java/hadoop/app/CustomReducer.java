package hadoop.app;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/*
 * Find number of unique zip codes in each state of ev charging dataset
 */
public class CustomReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		HashSet<Integer> st = new HashSet<>();
		values.forEach(val -> st.add(val.get()));
		context.write(key, new IntWritable(st.size()));
	}
}
