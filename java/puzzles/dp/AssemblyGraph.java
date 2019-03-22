public class AssemblyGraph{
	

	private final ModernAdjacencyList<Integer> graph; // 0 < j < n-1; i = 0, 1; processingTime[i][j] = time taken to process the chassis at station j in line i. 



	public Assembly(int[] processingTime, int[] switchingTime, int [] entry, int[] exit){
		if(processingTime.length != switchingTime.length) throw new IllegalArgumentException("Input arrays for the two lines are of unequal length");
		int n = processingTime.length
		this.graph = new ModernAdjacencyList<>(true, true);
		for(int i = 1; i < n; i++ ){
			graph.addVertex(i);

		}

		this.processingTimes = processingTime;
		this.switchingTime = switchingTime;
		this.entryTime = entry;
		this.exitTime = exit;
	}
}