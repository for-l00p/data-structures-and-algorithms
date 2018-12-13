import java.util.*;

// Unweighted graph.
// 
// 
public class IntGraph{
	
	private int vertexCount;
	private int edgeCount;
	private LinkedList<Integer>[] adj;


	public IntGraph(int n){

		vertexCount = n;
		edgeCount = 0;
		adj = (LinkedList<Integer>[]) new LinkedList[n];
		for(int i = 0; i<n;i++){
			adj[i] = new LinkedList<Integer>();
		}

	}


	public int vertexCount(){
		return vertexCount;
	}


	public void addEdge(int source, int destination){
		edgeCount++;
		adj[source].add(destination);
		adj[destination].add(source);
	}


	public Iterable<Integer> getNeighbours(int vertex){

		return new LinkedList(adj[vertex]);
	}

	public String toString() {
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
        s.append(vertexCount + " vertices, " + edgeCount + " edges " + NEWLINE);
        for (int v = 0; v < vertexCount; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }



	public static void main(String[] args){

		IntGraph test = new IntGraph(10);
		test.addEdge(0,1);
		test.addEdge(2,3);
		test.addEdge(0,2);
		test.addEdge(0,4);
		test.addEdge(4,5);
		// test.addEdge(0,9);
		test.addEdge(0,9);
		System.out.println(test.toString());


	}



}