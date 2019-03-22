/**
 * From the proof of Kruskal:
 * Minimum spanning tree is unique if edge weights are distinct. 
As the first edge, why don't we pick a higher weight edge? Say a tree has a  edge not part of Prim's MST. Add the first edge which departs from Prim's MST (say the minimum). This would form a cycle. But in this cycle, there ought to be atleast one edge greater than the edge added, which can be removed. 


Kruskal's algorithm thinking technique:
First think of algorithm high level (e.g. add edge unless it forms a cylcle)
Then think of how to implement low level steps (how to detect a cycle? And: if both the vertices in the edge are in the same connected components! How would you tell that? Can we store CC information in a data structure - as we iterate?)

Now think of what do you need from the data structure? What operation it supports? Write the running time as function of operations. Now think of different data structures and how they affect the running time. 


 */

import java.util.*;

public class Kruskal{

	private UnionFind uf;
	private Queue<Edge<Integer>> mst;
	private double weight;

	public Kruskal(ModernAdjacencyList<Integer> graph){

		mst = new LinkedList<>();
		weight = 0;
		uf = new UnionFind(graph.vertexCount());

		ArrayList<Edge<Integer>> edges = new ArrayList<>(graph.getEdges());
		Collections.sort(edges);

		for(Edge<Integer> edge: edges){
			//System.out.println("Considering edge: " + edge.print());
			int u = edge.source();
			int v = edge.destination();
			if(uf.connected(u,v)){
				//System.out.println("already connected:" + u + " and " + v);
				continue;
			} else {
				uf.union(u, v);
				//System.out.println("Adding edee to MST: " + edge.print());
				mst.add(edge);
				weight = weight + edge.weight;
				//System.out.println("weight is now: " + weight);
			}

		}
	}

	public Iterable<Edge<Integer>> edges(){

		System.out.println("Printing MST:");
		for(Edge<Integer> edge: mst){
			System.out.println(edge.print());
		}
		return mst;

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
      testGraph.addEdge(5, 7, 10);
      testGraph.addEdge(5, 7, 5);
      testGraph.addEdge(9, 0, 5);
      testGraph.addEdge(2, 5, 8);
      testGraph.addEdge(8, 7, 3);
      testGraph.addEdge(9, 7, 4 );
      testGraph.addEdge(5, 6, 7);

      testGraph.addEdge(6, 6);
      Kruskal testMST = new Kruskal(testGraph);
      //testMST.edges();
      

	}



}






