	
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

/*
 Running time: O(|V| + |E|). Sum of each bfs is O(|E|), but you might have to do it for all V in the worse case.

 */
public class ConnectedComponents<Z>{
	
	private Map<Z,Integer> componentsMap;
	private Map<Integer,Integer> componentSizeMap;
	private	Map<Z,Integer> visitedMap;
	private int count;
	


	public ConnectedComponents(Graph<Z,?> graph){

		visitedMap =  new HashMap<Z, Integer>();
		componentSizeMap = new HashMap<Integer, Integer>();
		componentsMap = new HashMap<Z, Integer>();
		count = 0;


		Iterable<Z> vertices = graph.getVertices();
		for(Z vertex: vertices){
			System.out.println("Marking as unvisited: " + vertex);
			visitedMap.put(vertex, 0);
		}


		int componentID = -1;
		for(Z source: vertices){
			
			if(!componentsMap.containsKey(source)){
				componentID++;
				count++;
				bfs(graph, source, componentID);	
			}
		}
	}


	private void bfs(Graph<Z,?> graph, Z source, int componentID){
		
		System.out.println("Starting BFS for: " + source + " in component " + componentID);

		Queue<Z> bfsQueue = new LinkedList<>();
		int componentSize = 1;
		bfsQueue.add(source);
		// visitedMap.put(source,1);	
		Z currentVertex = null;
		visitedMap.put(source,1);
		while(!bfsQueue.isEmpty()){
   			currentVertex = bfsQueue.poll();
   			componentsMap.put(currentVertex, componentID);
   			System.out.println("Putting " + currentVertex + " in component " + componentID);
   			componentSize++;
   			//System.out.println("Investigating neighbours of " + currentVertex);
   			Iterable<Z> neighbours = graph.getNeighbours(currentVertex);
   			for(Z neighbour: neighbours){
   				if(visitedMap.get(neighbour) == 0){
   					//System.out.println("Adding to queue " + neighbour);
   					visitedMap.put(neighbour,1);
   					bfsQueue.add(neighbour);
   				}
			}

		}

		componentSizeMap.put(componentID, componentSize);	
				
	}
	


	public static void main(String[] args){

      Graph<String, Edge<String>> testGraph = new ModernAdjacencyList<>(true);
      testGraph.addVertex("Delhi");
      testGraph.addVertex("Gurgaon");
      testGraph.addVertex("Chandigarh");
      testGraph.addVertex("Bangalore");
      testGraph.addVertex("Mumbai");
      testGraph.addVertex("London");
      testGraph.addVertex("Noida");
      testGraph.addVertex("Singapore");
      testGraph.addVertex("Thailand");
      testGraph.addVertex("Indonesia");
      testGraph.addEdge("Indonesia", "Thailand");
      testGraph.addEdge("Indonesia", "Noida");
      testGraph.addEdge("Delhi", "Noida");
      testGraph.addEdge("Delhi", "Mumbai");
      testGraph.addEdge("London", "Thailand");
      testGraph.addEdge("Mumbai", "Thailand");
      testGraph.addEdge("Chandigarh", "Gurgaon");
      testGraph.addEdge("Thailand", "Gurgaon");
      testGraph.addEdge("London", "Chandigarh");
      testGraph.addEdge("Noida", "Chandigarh");
      testGraph.addEdge("Chandigarh", "Delhi");
      testGraph.addEdge("Delhi", "Singapore");
      testGraph.addEdge("Indonesia", "Singapore");
      testGraph.addEdge("Indonesia", "Indonesia");
      System.out.println(testGraph.vertexCount());
      System.out.println(testGraph.edgeCount());
      System.out.println(testGraph.toString());
     
      ConnectedComponents<String>testCC = new ConnectedComponents<String>(testGraph);
      System.out.println(testCC.count);


	}
	




	



}