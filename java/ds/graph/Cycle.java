/**
 * API: Cycle
 *
 *
 *
 * Cycle(Graph)
 *
 * Cycle detection: Is a given graph acyclic? Cycle.java uses depth-first search to determine whether a graph has a cycle, and if so return one. It takes time proportional to V + E in the worst case.
 *
 *
 * Questions: 
 * Is the graph undirected? Yes
 * Does it have selfloops - and are they reported as a cycle? Yes. 
 * Does it have parallel edges - and are they reported as a cycle? Yes. 
 *
 * 
 */

import java.util.*;

public class DFSCycle{

	private boolean[] visited;
	private int[] parent;
	private int[] arr;
	private int[] dep;
	private int time;
	private Stack<Integer> cycle;

	public DFSCycle(IntGraph graph){

		if (hasSelfLoop(graph)) return;
		if (hasParallelEdges(graph)) return;

		int vertexCount = graph.vertexCount();
		visited = new boolean[vertexCount];
		parent = new int[vertexCount];
		arr = new int[vertexCount];
		dep = new int[vertexCount];
		detectCycleDFS(graph, 0);
		time = 0;
		for(int i = 0; i < vertexCount; i++){
			visited[i] = true;
		}
	}


	private boolean hasSelfLoop(IntGraph graph){

		
		for(int vertex = 0; vertex < graph.vertexCount(); vertex++){

			for(int neighbour: graph.getNeighbours(vertex)){
				if(neighbour == vertex){
					cycle = new Stack<Integer>();
					cycle.push(vertex);
					cycle.push(vertex);
					return true;
				}
				
			}
		}
		return false;

	}

	private boolean hasParallelEdges(IntGraph graph){

		visited = new boolean[graph.vertexCount()];

		for(int vertex = 0; vertex < graph.vertexCount(); vertex++){

			for(int neighbour: graph.getNeighbours(vertex)){
				if(visited[neighbour] == false){
					visited[neighbour] = true;
				} else {
					System.out.println("Found parallel edge");
					cycle = new Stack<Integer>();
					cycle.push(vertex);
					cycle.push(neighbour);
					cycle.push(vertex);
					return true;
				}
			}

			for(int neighbour: graph.getNeighbours(vertex)){
				visited[neighbour] = false;
			}
		}
		return false;


	}


	private void detectCycleDFS(IntGraph graph, int source){


		System.out.println("Visited:" + source + " at time " + time);
		time++;
	  	visited[source] = true;
	  	arr[source] = time;
	  	for(int vertex: graph.getNeighbours(source)){
	  		if(cycle != null) return;
	  		if(visited[vertex] == false){
	  			parent[vertex] = source;
	  			detectCycleDFS(graph, vertex);
	  		} else {
	  			if(vertex != parent[source]){

	  				System.out.println("Cycle detected at: " + vertex);
	  				cycle = new Stack<Integer>();
	  				int current = source;
	  				while(current != vertex){
	  					System.out.println("Pushing to stack: " + current);
	  					cycle.push(current);
	  					current = parent[current];
	  				}
	  				cycle.push(current);
	  				cycle.push(source);
	  				return;
	  					
	  			}
	  		}
	  	}
	  	time++;
	  	dep[source] = time;
	  	System.out.println("Departed:" + source + " at time " + time);


	}

	public Iterable<Integer> cycle(){
        return this.cycle;
    }

    public void printCycle(){
    	StringBuilder s = new StringBuilder();
		s.append("Cycle: ");
		for(Integer vertex: cycle()){
			s.append( vertex + "-> " );
		}
		System.out.println(s);
    }

    public boolean hasCycle(){
    	return (cycle != null);
    }


	public static void main(String[] args){
		IntGraph test = new IntGraph(10);
		test.addEdge(0,1);
		test.addEdge(2,3);
		test.addEdge(0,2);
		test.addEdge(0,4);
		test.addEdge(4,5);
		test.addEdge(0,9);
		test.addEdge(1,6);
		test.addEdge(9,8);
		test.addEdge(7,3);
		test.addEdge(7,8);

		System.out.println(test.toString());
		DFSCycle findCycle = new DFSCycle(test);
		if(findCycle.hasCycle()){
			findCycle.printCycle();
		}

	}
	


}