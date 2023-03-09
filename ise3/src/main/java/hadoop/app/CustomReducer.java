package hadoop.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

class Row {

	Double level1, level2, fast_dc;
	public Row(Double level1, Double level2, Double fast_dc) {
		this.level1 = level1;
		this.level2 = level2;
		this.fast_dc = fast_dc;
	}
}

public class CustomReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		ArrayList<Double> level1 = new ArrayList<>();
		ArrayList<Double> level2 = new ArrayList<>();
		ArrayList<Double> fast_dc = new ArrayList<>();
		values.forEach(i -> {
			String x[] = i.toString().split(",");
			level1.add(Double.parseDouble(x[0]));
			level2.add(Double.parseDouble(x[1]));
			fast_dc.add(Double.parseDouble(x[2]));
		});
		
		Double MAX_LEVE_1 = level1.stream().reduce(0.0, Double::sum);
		Double MAX_LEVE_2 = level2.stream().reduce(0.0, Double::sum);
		Double MAX_FAST_DC = fast_dc.stream().reduce(0.0, Double::sum);

		StringBuilder result = new StringBuilder();
		result.append("LEVEL1:" + MAX_LEVE_1 +" ,LEVEL2:"+MAX_LEVE_2+" ,FAST_DC:"+MAX_FAST_DC);

		Double MAXI = Collections.max(Arrays.asList(MAX_LEVE_1, MAX_LEVE_2, MAX_FAST_DC));
		if(MAXI == MAX_LEVE_1) {
			result.append("    MAX:" + MAXI + ", LEVEL1");
		}
		else if(MAXI == MAX_LEVE_2) {
			result.append("       MAX:" + MAXI + ", LEVEL2");
		}
		else {
			result.append("       MAX:" + MAXI + ", FAST_DC");
		}
		context.write(key, new Text(result.toString()));
	}
}
