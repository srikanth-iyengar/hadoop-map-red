package hadoop.app;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
		ArrayList<Double> numbers = new ArrayList<>();
		values.forEach(val -> numbers.add(val.get()));
		context.write(new Text("Max element"), new DoubleWritable(numbers.stream().max(Double::compare).get()));
	}
}
