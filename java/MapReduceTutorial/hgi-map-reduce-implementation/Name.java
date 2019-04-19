package HGI;


interface Processable {

	public void processLine(String line){

	}
}


public class Name {

	Map<String, String> userIdMap;
	Map<String, String> groupIdMap;

	private String readFile(String filePath, Processable fileProcessor){
		InputStream in = .readFile(filePath)
		Striing line = in.readLine();
		fileProcessor.processLine(line)
	}
	
	public String getUserNameGivenId(String uid){
		readFile('./etc/passwd', (String line) -> {
			String[] recordValues =  line.split("\t");
			String userName = 
			userIdMap.put()
		})
	};

	public String getGroupNameGivenId(String gid){
		readFile('./etc/group')
	};




}