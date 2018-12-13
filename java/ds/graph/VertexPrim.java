


import java.util.*;

public class VertexPrim{


	private IndexedMinPQ<Double> vertexHeap;
	private boolean[] visited;
	private double[] distTo;
	private ArrayList<Edge<Integer>> edgeTo;

	private double weight;
	private Queue<Edge<Integer>> mst;


	public VertexPrim(ModernAdjacencyList<Integer> graph){

		int n = graph.vertexCount();
		mst = new LinkedList<>();
		weight = 0;
		visited = new boolean[n];
		vertexHeap = new IndexedMinPQ<Double>(n);
		distTo = new double[n];
		edgeTo = new ArrayList<>(n);


		
		for(int i = 0; i < n; i++){
			distTo[i] = Double.POSITIVE_INFINITY;
			visited[i] = false;
			//vertexHeap.insert(i, distTo[i]);
			edgeTo.add(i, null);
		}

		
		for(int i = 0; i < n; i++){
			if(visited[i] == false){
				System.out.println("Called prim on vertex " + i );
				prim(graph, i);
			}
		}

		for(Edge<Integer> edge: edgeTo){
			if(edge != null){
				//System.out.println(edge.print());
				mst.add(edge);
				weight = weight + edge.weight;
			}
			
		}




	}

	private void prim(ModernAdjacencyList<Integer> graph, Integer root){

		distTo[root] = 0;
		vertexHeap.insert(root, distTo[root]);
		//scan(gotraph, root);
		while(!vertexHeap.isEmpty()){
			Integer v = vertexHeap.extractMin();
			if(visited[v] == false){
				scan(graph, v);
			}

		}


	}


	private void scan(ModernAdjacencyList<Integer> graph, Integer vertex){

		System.out.println("scanning " + vertex );

		for(Edge<Integer> edge : graph.outIncidentEdges(vertex)){
			
			Integer u = edge.opposite(vertex);
			//System.out.println("Analyzing edge: " + edge.print() + " with opposite vertex " + u);
			if(visited[u] == false){
				//System.out.println("Comparing weight of edge " +  edge.print()  + " with distTo on vertex " +  u + ": "  + edge.weight + " and "  + distTo[u]);
				if(edge.weight < distTo[u]){
					distTo[u] = edge.weight;
					edgeTo.set(u, edge);
					//System.out.println("Changing distTo of  " + u + " to: " + distTo[u]);
					if(vertexHeap.contains(u)){
						vertexHeap.changeKey(u, distTo[u]);
					} else {
						vertexHeap.insert(u,distTo[u]);
					}
					
				}
		
			}
			
		}

		visited[vertex] = true;
		//System.out.println("adding edge to mst: " + edgeTo.get(vertex).print() );
		//mst.add(edgeTo.get(vertex));
	}


	public Iterable<Edge<Integer>> edges(){

		System.out.println("Printing MST:");
		for(Edge<Integer> edge: mst){
			System.out.println(edge.print());
		}
		return mst;

	}

	public double weight(){

		return this.weight;
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
      //testGraph.addEdge(6, 6);
      VertexPrim testMST = new VertexPrim(testGraph);
      testMST.edges();
      System.out.println(testMST.weight());

	}





	
}