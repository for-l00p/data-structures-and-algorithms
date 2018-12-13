
/**
 * Combinatorial Insight: Any vertex that is not on a directed cycle forms a strongly connected component all by itself: for example, a vertex whose in-degree or out-degree is 0, or any vertex of an acyclic graph. 
 * 
 * 
 * Combinatorial Insight: A  strongly connected components form subtrees of the DFS tree. (In other words, a component can not be in two separate parts of the tree.)
 *
 * Combinatorial Insight: Consider a vertex v just about to be explored in DFS via edge e. 
 * 
 * Case 1:  The subtree rooted rooted at v has an edge going out from it to an ancestor of v (either a back edge to ancestor of vertex or cross-edge to another subtree) => e  is part a cycle in particular, and v is a part of an already discovered SCC. 
 * 
 * Case 2: no edge from the subtree rooted at v goes out of the subtree to an ancestor of v , then the edge preciding that vertex connects two different strongly connected components, one already explored and one yet to be explored.  To find components, we just have to break tree at certain edges, and the components will be formed by what's left of the tree. We'll say a vertex is a "head" of a component if it's the topmost (i.e. if we should break the edge coming into it).
 * 
 *   By the observations above, the problem has turned into one of determining whether a given vertex v is a head.
 *   Case 1:  head[v] < v
 *   Case 2:  head[v] = v
 *   
 * If we consider SCC as subtrees in DFS; the vertex, thus, is the root of a strongly connected component (though the subtree might contain several SCCs).  
 *
 * https://www.ics.uci.edu/~eppstein/161/960220.html
 * 
 */


import java.util.*;

public class StronglyConnected{
	
	private boolean[] visited;
	private int[] arr;
	private int[] dep;
	private int[] parent;
	private int[] head;
	private int time;
	private int count;
	private Stack<Integer> sccStack;



	public StronglyConnected(Graph<Integer,?> graph){

		int n = graph.vertexCount();
		visited = new boolean[n];
		arr = new int[n];
		dep = new int[n];
		parent = new int[n];
		head = new int[n];
		count = 0;
		sccStack = new Stack<Integer>();



		

		for(int i = 0; i < n; i++){

			if(visited[i] == false){
				dfs(graph, i);
			}

		}

		System.out.println("Head array:" + Arrays.toString(head));
		

	}


	public Integer dfs(Graph<Integer,?> graph, Integer source){


		time++;
		arr[source] = time;
		visited[source] = true;
		sccStack.push(source);
		int earliestArrivalEdge = arr[source];
		System.out.println("Arrived at vertex:" + source);

		for(int neighbour: graph.getNeighbours(source)){
			if(visited[neighbour] == false){
				parent[neighbour] = source;
				Integer earliestOfSubtree = dfs(graph, neighbour);
				if(earliestOfSubtree != null){
					earliestArrivalEdge = Math.min(earliestArrivalEdge, earliestOfSubtree);
				}
			} else {
				if(neighbour == parent[source]){
					continue;
				} else {
					earliestArrivalEdge = Math.min(arr[neighbour], earliestArrivalEdge);
					// Question: Can we consider earliestArrivalEdge(neighbour)? Only in the case of cross edge, because for backedges this wouldn't be calculated and we would run into trouble. 
				}
			}
		}

		if(earliestArrivalEdge == arr[source]){
			System.out.println("No edge out of the subtree rooted at vertex " + source + ". Now printing SCC:");
			count++;	
			StringBuilder s = new StringBuilder();
			Integer vertex = null;
			while(vertex != source){
				vertex = sccStack.pop();
				head[vertex] = source;
				s.append(vertex);
				
			}
			System.out.println(s);
			return null;
		} 
		
		return earliestArrivalEdge;


	}

	private boolean isStronglyConnected(){
		return count == 1;
	}


	private int count(){
		return count;
	}


	 public static void main(String[] args){

    
      Graph<Integer, Edge<Integer>> testGraph = new ModernAdjacencyList<>(true);
      testGraph.addVertex(0);
      testGraph.addVertex(8);
      testGraph.addVertex(1);
      testGraph.addVertex(9);
      testGraph.addVertex(2);
      testGraph.addVertex(6);
      testGraph.addVertex(3);
      testGraph.addVertex(4);
      testGraph.addVertex(7);
      testGraph.addVertex(5);
      testGraph.addEdge(5, 7);
      testGraph.addEdge(5, 3);
      testGraph.addEdge(0, 3);
      testGraph.addEdge(0, 2);
      //testGraph.addEdge(6, 7);
      testGraph.addEdge(2, 7);
      testGraph.addEdge(1, 8);
      testGraph.addEdge(7, 8);
      //testGraph.addEdge(6, 1);
      //testGraph.addEdge(3, 1);
      //testGraph.addEdge(1, 0);
      testGraph.addEdge(8, 5);
      testGraph.addEdge(3, 6);
      testGraph.addEdge(8, 9);
      testGraph.addEdge(0, 5);
      testGraph.addEdge(0, 4);
      testGraph.addEdge(5, 4);
      testGraph.addEdge(9, 4);
      testGraph.addEdge(4, 7);
      testGraph.addEdge(9, 1);
      System.out.println(testGraph.vertexCount());
      System.out.println(testGraph.edgeCount());
      System.out.println(testGraph.toString());
      StronglyConnected testSC = new StronglyConnected(testGraph);
      System.out.println(testSC.count());

    }  








}