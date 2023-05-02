package hadoop.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomReducer extends Reducer<Text, Text, Text, Text> {
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		ArrayList<String> mac = new ArrayList<>();
		ArrayList<String>  port = new ArrayList<>();
		values.forEach(val -> {
			port.add(val.toString());
		});
		StringBuilder sb = new StringBuilder();
		sb.append("port: " + Collections.max(port));
		context.write(key, new Text(sb.toString()));
	}
}
