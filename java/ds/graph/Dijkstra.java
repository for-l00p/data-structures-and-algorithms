/**
 * 
Combinatorial Insight: Shortest path between a pair of vertices contains the shortest path between every pair of vertices. So solving for all would assist in solving the particular. 


 */

 
 import java.util.*;

 public class Dijkstra{


 	private boolean[] visited;
 	private ArrayList<Edge<Integer>> edgeTo;
 	double[] distTo;
 	private IndexedMinPQ<Double> vertexHeap;


 	public Dijkstra(ModernAdjacencyList<Integer> graph, Integer source){

 		int n = graph.vertexCount();
 		visited = new boolean[n];
 		distTo = new double[n];
 		edgeTo = new ArrayList<>(n);
 		vertexHeap = new IndexedMinPQ<Double>(n);

 		for(int i = 0; i < n; i++){
 			edgeTo.add(null);
 			distTo[i] = Double.POSITIVE_INFINITY;
 			visited[i] = false;
 		}

 		calculateDistance(graph, source);

 	}


 	private void calculateDistance(ModernAdjacencyList<Integer> graph, Integer source){

 		distTo[source] = 0;
 		visited[source] = true;
 		vertexHeap.insert(source, distTo[source]);

 		while(!vertexHeap.isEmpty()){
 			Integer u = vertexHeap.extractMin();
 			scan(graph, u);

 		}
 	}

 	private void scan(ModernAdjacencyList<Integer> graph, Integer u){

 		System.out.println("Scanning  " + u);
 		for(Edge<Integer> edge: graph.outIncidentEdges(u)){
 				Integer v = edge.opposite(u);
 				System.out.println("Analyzing edge: " + edge.print() + " with opposite vertex " + v);
 				if(visited[v] == false){
 					if(distTo[v] > distTo[u] + edge.weight){
 						distTo[v] = distTo[u] + edge.weight;
 						System.out.println("Changing distTo of  " + v + " to: " + distTo[v]);
 						edgeTo.set(v, edge);
 						if(vertexHeap.contains(v)){
 							vertexHeap.changeKey(v, distTo[v]);
 						} else {
 							vertexHeap.insert(v, distTo[v]);
 						}
 					
 					}
 				}
 			}
 			visited[u] = true;
 	}

 	public double distanceTo(Integer vertex){
 		return distTo[vertex];
 	}


 	public static void main(String[] args){

 	ModernAdjacencyList<Integer> testGraph = new ModernAdjacencyList<>();
	  testGraph.addVertex(0);
      testGraph.addVertex(1);
      testGraph.addVertex(2);
      testGraph.addVertex(3);
      testGraph.addVertex(4);
      testGraph.addVertex(5);
      testGraph.addVertex(6);
      testGraph.addVertex(7);
      testGraph.addVertex(8);
      testGraph.addVertex(9);
      testGraph.addEdge(0, 1,  4);
      testGraph.addEdge(1, 2,  8);
      testGraph.addEdge(2, 3, 7);
      testGraph.addEdge(3, 4, 9);
      testGraph.addEdge(4, 5, 10);
      testGraph.addEdge(3, 5, 14);
      testGraph.addEdge(5, 6, 2);
      testGraph.addEdge(6, 7, 1);
      testGraph.addEdge(7, 0, 8);
      testGraph.addEdge(1, 7, 11);
      testGraph.addEdge(2, 8, 2 );
      testGraph.addEdge(8, 6, 6);
      testGraph.addEdge(7, 8, 7);
      testGraph.addEdge(2, 5, 4);
      System.out.println(testGraph.toString());
      Dijkstra test = new Dijkstra(testGraph, 0);
      System.out.println(test.distanceTo(9));
      System.out.println(Arrays.toString(test.distTo));

 	}


 }

