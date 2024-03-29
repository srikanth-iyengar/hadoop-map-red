package hadoop.app;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        if(args.length < 2) {
        	System.err.println("Usage: Weather Condition <input path> <output path> <arguments>");
        	System.exit(-1);
        }
        Configuration conf = new Configuration();

        if(args.length >= 3) {
            conf.set("args-2", args[2]);
        }

        Job job = new Job(conf);
        

        job.setJarByClass(App.class);
        job.setJobName("Even Odd");

        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        job.setMapperClass(CustomMapper.class);
        job.setReducerClass(CustomReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
