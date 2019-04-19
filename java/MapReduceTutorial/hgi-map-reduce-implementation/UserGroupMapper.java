package HGI;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class UserGroupMapper extends MapReduceBase implements Mapper <LongWritable, Text, Text, Text> {

	
	public void map(LongWritable key, Text value, OutputCollector <Text, Text> output, Reporter reporter) throws IOException {
		
		String valueString = value.toString();
		String[] iNodeData = valueString.split("\t");
		String uid = iNodeData[2];
		String gid = iNodeData[3];
		String userName = Name.getUserNameGivenId(uid);
		String groupName = Name.getGroupNameGivenId(gid)
		String newKey = userName+ ":" + groupName;
		output.collect(new Text(newKey), value);
	}
}

/**
0. File path (base64 encoded)
1. Size (bytes)
2. Owner (UID)
3. Group (GID)
4. Last accessed time (atime; Unix epoch)
5. Last modified time (mtime; Unix epoch)
6. Last changed time (ctime; Unix epoch)
File type (see below)
Inode ID
Number of hardlinks
Device ID

L3Byb2plY3RzL21hc2hpZS9mb29iYXIvcmVzb3VyY2VzL25lYW5kZXJ0aGFsX2hhcF9yZWljaC9JQlMuaGFwbWFwL3N1bW1hcmllcy9oYXBsb3R5cGVzL2Noci01OTMudGhyZXNoLTU5My5sZW5ndGgtMC4wMC5oYXBsb3R5cGVzLmd6	3769	15029	201	1491492358	1386638974	1491238011	f	144116439891055895	1	1369159358