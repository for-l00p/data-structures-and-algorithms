
/******************************************************************************
 *  Compilation:  javac BFSTree.java
 *  Execution:    java BFSTree
 *  Dependencies: Graph.java 
 *  Data files:   
 *
 *  Run breadth first search on a graph.
 *  Runs in O(E + V) time.

 *
 ******************************************************************************/


/**
 * Notes:
 *
 * A graph is bipartite <-> any BFS tree does not have an edge between two vertices on the same level <-> there are no odd cycles in the graph
 * 
 */
import java.util.*;

public class BFSTree<T>{

  // We could use an array (with indicies representing vertices)instead of maps. 
	  private Queue<T> bfsQueue; 
   	private Map<T,Integer> visitedMap;
   	public  Map<T,Integer> distanceMap; 
   	private Map<T,T> parentsMap; 
    private boolean isBipartite;


    public BFSTree(){
      this.bfsQueue = new LinkedList<T>();
      this.visitedMap = new HashMap<T,Integer>();
      this.distanceMap = new HashMap<T,Integer>();
      this.parentsMap = new HashMap<T,T>(); 
    }


  	public BFSTree(Graph<T, ?> graph, T source){
  		this.bfsQueue = new LinkedList<T>();
  		this.visitedMap = new HashMap<T,Integer>();
   		this.distanceMap = new HashMap<T,Integer>();
   		this.parentsMap = new HashMap<T,T>();
       this.isBipartite  = true;
      for(T vertex: graph.getVertices()){
        visitedMap.put(vertex,0);
      }
      traverse(graph, source);
  	}

	
	/*************************************************************************
     					Traversals
   ***************************************************************************/
	
   public void traverse(Graph<T, ?> graph, T source){

      
   		bfsQueue.add(source);
   		visitedMap.put(source,1);
   		distanceMap.put(source,0);
   		parentsMap.put(source, null);
      T currentVertex = null;
   		while(!bfsQueue.isEmpty()){
   			currentVertex = bfsQueue.poll();
        if(edgeOnSameLevel(currentVertex,bfsQueue,graph)){
          this.isBipartite = false;
        }

   			Iterable<T> neighbours = graph.getNeighbours(currentVertex);
   			for(T vertex: neighbours){
   				if (visitedMap.get(vertex) == 0){
   					bfsQueue.add(vertex);
   					visitedMap.put(vertex,1);
   					parentsMap.put(vertex,currentVertex);
            int distance = distanceMap.get(currentVertex) + 1;
   					distanceMap.put(vertex, distance);
            System.out.println("Visited: " + vertex + " which is at distance " + (distanceMap.get(currentVertex) + 1));

   				}
   			}
   			visitedMap.put(currentVertex,2);
   		}
   }


   private boolean edgeOnSameLevel(T source, Queue<T> bfsQueue, Graph<T, ?> graph){
        for(T vertex: bfsQueue){
          if(graph.isNeighbour(source, vertex)){
            System.out.println("Vertices on same level of BFS Tree: " + source + " and " + vertex);
            return true;
          }
        }
        return false;
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
      BFSTree<String> testBFS = new BFSTree<>(testGraph, "Chandigarh");
      System.out.println(testBFS.isBipartite);

  }


}