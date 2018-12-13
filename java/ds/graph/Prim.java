/**
 * Notes:
 * 

For Prim:

Combinatorial insight: Take any cut of the graph. The minimum edge in that cut will be in MST (there could be more, but the minimum is a must). This can be used to check whether a T is a spanning tree: if there is a cut whose minimum is not part of the Tree, then that tree is not MST. 

Write the algorithm in high level pictorial concepts.

What is the operation you have to do at each step? 

At any point you have the following: a Set S. You have to maintain that collection of vertices which are on one side. (This can be done very easily by maintaining a bit for each vertices, e.g.)
Then you have to look at the edges out from the set, and find its minimum. How can you do this?

----

Naive way: Look at all the vertices. Look at the adjacent vertices. Check if they are in S. If not, find the edges and their minimum. Time Complexity: O(m) at each step. O(mn) in total for the algorithm.

Better way: Store the cut edges in a separate data structure. Then update it as you  transfer vertices from the unvisited set to visited set. To find the next vertex and add the edge, the operation you need to optimize for is finding the minimum of the edge. 

A heap  allows you to find minimum in constant time, so you can store the edges in a heap. Each step will take deg(v) logm, and the total running time would be O(mlogm)


On time complaxity: https://stackoverflow.com/questions/4825518/how-to-implement-prims-algorithm-with-a-fibonacci-heap

http://www.cs.princeton.edu/~wayne/cs423/lectures/heaps-4up.pdf
https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LazyPrimMST.java.html




**/
import java.util.*;


public class Prim{

	private Queue<Edge<Integer>> mst;
	private int mstWeight;
	private Heap<Edge<Integer>> cutHeap;
	private int[] visited;



	public Prim(ModernAdjacencyList<Integer> graph){

		int n = graph.vertexCount();
		visited = new int[n];
		cutHeap = new Heap<Edge<Integer>>();
		mst = new LinkedList<Edge<Integer>>();
		mstWeight = 0;
		

		for(int i = 0; i < n; i++){
			if(visited[i] == 0){
				System.out.println("Starting building MST on vertex: " + i);
				primIterative(graph, i);
			}
		}
	}


	private void primIterative(ModernAdjacencyList<Integer> graph, int root){


		visited[root] = 1;

		for(Edge<Integer> edge: graph.outIncidentEdges(root)){
			System.out.println("Adding to cut: " + edge.print());
			cutHeap.insert(edge);
		}

		while(!cutHeap.isEmpty()){


			Edge<Integer> minEdge = cutHeap.extractMin();
			
			System.out.println("weight is now: " + mstWeight);
			Integer u = minEdge.source();
			Integer v = minEdge.destination();

			if(visited[u] == 1 && visited[v] == 1){
				System.out.println("Both vertices visited: " + u + v);
				continue;
			}

			if(visited[u] == 0 && visited[v] == 0){
				throw new IllegalArgumentException("Both vertices are unvisited. Edge shouldnt belong to the cut");
			}

			mst.add(minEdge);
			System.out.println("Adding to MST: " + minEdge.print());
			mstWeight = mstWeight + minEdge.weight;

			Integer nextVertex = visited[u] == 1 ? v : u;
			visited[nextVertex] = 1;
			for(Edge<Integer> nextEdge: graph.outIncidentEdges(nextVertex)){

				if(visited[nextEdge.opposite(nextVertex)] == 0){
					System.out.println("Adding to cut: " + nextEdge.print());
					cutHeap.insert(nextEdge);
				} else {
					//System.out.println("Removing from cut: " + nextEdge.print());
					//cutHeap.delete(nextEdge);
				}
			}	

		}
	}
	


	public void printMST(){

		for(Edge<Integer> edge: mst){
			System.out.println(edge.print() + "(" + edge.weight + ")");
		}
		

	}	

	public int weight(){
		return this.mstWeight;
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
      testGraph.addEdge(0, 1,  1);
      testGraph.addEdge(1, 2,  2);
      testGraph.addEdge(2, 3, 5);
      testGraph.addEdge(3, 0, 4);
      testGraph.addEdge(0, 2, 3);

      testGraph.addEdge(5, 7);
      testGraph.addEdge(8, 7);
      testGraph.addEdge(9, 7);
      testGraph.addEdge(5, 6);
      Prim testMST = new Prim(testGraph);

	}
}

