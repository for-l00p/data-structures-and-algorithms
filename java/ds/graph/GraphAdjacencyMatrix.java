import java.util.*;

//The key method adj() allows client code to iterate through the vertices adjacent to a given vertex. Remarkably, we can build all of the algorithms that we consider in this section on the basic abstraction embodied in adj().

public class GraphAdjacencyMatrix implements Graph<Integer, Edge<Integer> >{
	
	private int[][] matrix;

	//unweighted, undirected for now. 

	public GraphAdjacencyMatrix(int n){

		this.matrix = new int[n][n];

		for(int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				matrix[i][j] = 0;
			}
		}
	}

	public boolean isEmpty(){

		return (matrix.length == 0);

	}


	public int vertexCount(){
		return matrix.length;
	}

 
	public int edgeCount(){
		int count = 0;
		int n = matrix.length;
		for(int i = 0; i < n; i++){
			for (int j = 0; j < i; j++){
				if(matrix[i][j] == 1){
					count++;
					System.out.println("Found edge between: " + i + " and " + j);
				}
			}
		}


		return count;

	}

		
	// public Set<Edge<Integer>> getEdges(){

	// 	int n = matrix.length;
	// 	Set<Edge<Integer>> edges = new HashSet<>();
	// 	for(int i = 0; i < n; i++){
	// 		for (int j = 0; j < n; j++){
	// 			if(matrix[i][j] == 1){
	// 				edges.add(new Edge<>(i,j));
	// 			}
	// 		}

	// 	}

	// 	return edges;

	// }

	public Set<Integer> getVertices(){

		int n = matrix.length;
		Set<Integer> vertices = new HashSet<>();
		for(int i = 0; i < n;i++){
			vertices.add(i);
		}	
		return vertices;


	}



	public boolean addVertex(Integer vertex){

		int n = matrix.length;
		int[][] temp = matrix;
		int[][] matrix = new int[n+1][n+1];
		for(int i = 0; i < n + 1; i++){
			for (int j = 0; j < n + 1; j++){
				if(i == n || j == n){
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = temp[i][j];
				}
			}
		}
		return true;	
	}	



	public boolean addEdge(Integer source, Integer destination){

		// validateVertex(source);
		// validateVertex(destination);
		matrix[source][destination] = 1;
		matrix[destination][source] = 1;
		return true;

	}


	// public void removeVertex(Integer vertex){

	// }

	public void removeEdge(Integer source, Integer destination){


		matrix[source][destination] = 0;
		//return new Edge<>(source, destination);
	}


	public Set<Integer> getNeighbours(Integer vertex){

		int n = matrix.length;
		Set<Integer> vertices = new HashSet<>();
		for(int j = 0; j < n;j++){
			if(matrix[vertex][j] == 1){
				vertices.add(j);
			}
			
		}	

		return vertices;

	}


	// public Set<Edge<Integer>> inIncidentEdges(Integer vertex){

	// 	int n = matrix.length;
	// 	Set<Edge<Integer>> edges = new HashSet<>();
	// 	for(int j = 0; j < n;j++){
	// 		if(matrix[j][vertex] == 1){
	// 			edges.add(new Edge<>(j, vertex));
	// 		}
			
	// 	}

	// 	return edges;

	// }



	public Set<Edge<Integer>> outIncidentEdges(Integer vertex){
		int n = matrix.length;
		Set<Edge<Integer>> edges = new HashSet<>();
		for(int j = 0; j < n;j++){
			if(matrix[vertex][j] == 1){
				edges.add(new Edge<>(vertex, j));
			}
			
		}

		return edges;


	}

	public boolean isNeighbour(Integer vertex1, Integer vertex2){

		return (matrix[vertex1][vertex2] == 1) && (matrix[vertex2][vertex1] == 1) ;
	}


	// public int degree(Integer vertex){

	// 	return (inDegree(vertex) + outDegree(vertex));

	// }

	// public int inDegree(Integer vertex){

	// 	int result = 0;
	// 	int n = vertexCount();
	// 	for(int j = 0; j < n && j != vertex; j++){
	// 		if(matrix[j][vertex] == 1){
	// 			result++;
	// 		}
	// 	}
		
	// 	return result;

	// }



	public int outDegree(Integer vertex){

		int result = 0;
		int n = matrix.length;
		for(int j = 0; j < n && j!= vertex; j++){
			if(matrix[vertex][j] == 1){
				result++;
			}
		}

		return result;

	}



	public static void main(String[] args){

		GraphAdjacencyMatrix test = new GraphAdjacencyMatrix(10);
		test.addEdge(0, 1);
		System.out.println(test.vertexCount());
		System.out.println(test.edgeCount());

	}



}