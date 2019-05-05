
import java.util.ArrayList;

import java.util.*;
import java.io.*;


public class IntGraph {
	
	private final List<List<Integer>> adjList;
	int numEdges;
	int numVertices;

	public IntGraph (int v){
		adjList = new ArrayList<>();
		for (int i = 0; i < v; i++){
			adjList.add(new ArrayList<>());
			numVertices++;
		}
	}

	 public IntGraph(String filePath) {
        try {

        	File file = new File(filePath);
			Scanner in = new Scanner(file);
            this.numVertices = in.nextInt();
            if (numVertices < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adjList = new ArrayList<>();
            for (int v = 0; v < numVertices; v++) {
                adjList.add(new ArrayList<>());
            }

            int numEdges = in.nextInt();
            if (numEdges < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < numEdges; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                //validateVertex(v);
                //validateVertex(w);
                addEdge(v, w); 
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }


	public void addEdge(int v, int w){
		List<Integer> adjFirst = adjList.get(v);
		List<Integer> adjSecond = adjList.get(w);
		if (adjFirst.contains(w)) throw new IllegalArgumentException("Edge already present: " + v + "-> " + w );
		if (adjSecond.contains(v)) throw new IllegalArgumentException("Edge already present: " + w + "-> " + v );
		adjFirst.add(w);
		adjSecond.add(v);
		numEdges++;
	}

	public Iterable<Integer> adj(int v){
		return new ArrayList<Integer>(adjList.get(v));
	}

	public int numVertices(){
		return numVertices;
	}

	public int numEdges(){
		return numEdges;
	}


	public String toString(){

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < numVertices; i++){
			s.append(i);
			s.append(": ");
			for (Integer w: adjList.get(i)){
				s.append(w);
				s.append(" ");
			}
			s.append("\n");
		}
		return s.toString();

	}


	public static void main(String[] args){

		IntGraph test = new IntGraph(9);
		test.addEdge(0,3);
		test.addEdge(0,1);
		test.addEdge(1,4);
		test.addEdge(1,2);
		test.addEdge(2,3);
		test.addEdge(5,6);
		test.addEdge(6,7);
		test.addEdge(7,7);
		test.addEdge(2,8);
		
		// String filePath = "./tinyG.txt";
		// //String filePath = args[0];
		// IntGraph test = new IntGraph(filePath);

		//test.addEdge(1,0);
		//  System.out.println(test.toString());
		// DFSIterator it = new DFSIterator(test, 0);
		// System.out.println("DFS Iterator:");
		// while (it.hasNext()){
		// 	System.out.println(it.next());
		// }

		// System.out.println("DFS Iterative 1:");
		// GraphSearch.dfsIterative(test, 0);
		// System.out.println("DFS Iterative 2:");
		// GraphSearch.dfsIterative2(test, 0);

		System.out.println("BFS Iterator:");
		BFSIterator it2 = new BFSIterator(test, 0);
		while (it2.hasNext()){
			System.out.println(it2.next());
		}
		System.out.println("BFS Iterative:");
		GraphSearch.bfsIterative(test, 0);
		// System.out.println("DFS Iterative Sedgewick:");
		// NonrecursiveDFS check = new NonrecursiveDFS(test, 0);


		
		
	}






}