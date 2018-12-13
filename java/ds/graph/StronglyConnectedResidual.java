/*
 observe that the strongly connected components of a directed graph form an acyclic “meta-graph”,
where the meta-nodes correspond to the SCCs C1, . . ., Ck, and there is an arc Ch → Cℓ with h 6= ℓ if and
only if there is at least one arc (i, j) in G with i ∈ Ch and j ∈ Cℓ. This directed graph must be acyclic:
since within a SCC you can get from anywhere to anywhere else on a directed path, in a purported directed
cycle of SCCs you can get from every node in a constituent SCC to every other node of every other SCC
in the cycle. Thus the purported cycle of SCCs is actually just a single SCC. Summarizing, every directed
graph has a useful “two-tier” structure: zooming out, one sees a DAG on the SCCs of the graph; zooming
in on a particular SCC exposes its finer-grained structure
 */

public class StronglyConnectedResidual{
	
	private boolean isStronglyConnected;

	public StronglyConnectedResidual(Graph<Integer,?> graph){

		Graph<Integer,?> residualGraph = residualGraph(graph);

		DFSTree<Integer> graphDFS = new DFSTree<>(graph,0);
		System.out.println("Are all vertices reachable from 0?: " + graphDFS.isConnected());
		DFSTree<Integer> residualDFS = new DFSTree<>(residualGraph,0);
		System.out.println("Are all vertices reachable to 0?: " + residualDFS.isConnected());
		this.isStronglyConnected =  graphDFS.isConnected() && residualDFS.isConnected();

	}



	private Graph<Integer, ?> residualGraph(Graph<Integer, ?> graph){

		Graph<Integer,?> residualGraph = new ModernAdjacencyList<>(true);
		for(int i = 0; i < graph.vertexCount(); i++){
			residualGraph.addVertex(i);
		 	for(Integer vertex: graph.getNeighbours(i)){
		 		//residualGraph.removeEdge(i,vertex);
		 		residualGraph.addVertex(vertex);
		 		residualGraph.addEdge(vertex,i);
		 		System.out.println("reversed edge: " + i + "-->  " + vertex);
		 	}
		}
		System.out.println(residualGraph.toString());
		return residualGraph;
	}

	public boolean isStronglyConnected(){
		return isStronglyConnected;
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
      testGraph.addEdge(6, 7);
      testGraph.addEdge(2, 7);
      testGraph.addEdge(1, 8);
      testGraph.addEdge(7, 8);
      testGraph.addEdge(6, 1);
      testGraph.addEdge(3, 1);
      testGraph.addEdge(1, 0);
      testGraph.addEdge(8, 5);
      testGraph.addEdge(3, 6);
      testGraph.addEdge(8, 9);
      testGraph.addEdge(0, 5);
      testGraph.addEdge(0, 4);
      testGraph.addEdge(5, 4);
      testGraph.addEdge(9, 4);
      testGraph.addEdge(4, 7);
      System.out.println(testGraph.vertexCount());
      System.out.println(testGraph.edgeCount());
      System.out.println(testGraph.toString());
      StronglyConnectedResidual testSC = new StronglyConnectedResidual(testGraph);
      System.out.println(testSC.isStronglyConnected());

  }


}