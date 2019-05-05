
/**

Assorted Graph Notes:

A tree is a connected graph with no cycles. 

A tree with n nodes has n-1 edges (Proof by induction)
If the #Edges < n-1, then the graph cannot be connected (Proof by contradiction with earlier assertion that tree has n-1 edges)

An eulerian tour (visit each edge exactly once and return) exists iff only all vertices have even degree (and ofcourse graph has to be connected)

(For comparison: Hamiltonian Cycle visites each vertex exactly once (NP Complete - NP hard && NP - cannot be solved in polynomial time but a yes instance can be verified in polynomial time), Travelling Salesman visits each vertex as many times but is the shortest such path (NP Hard but not NP- cannot be solved in polynomal time, a yes instance cannot be verified to be shortest in polynomial time ), decison variant of TSP (path weighng < k is NP Complete NP hard and an instance can be measured in polynomial time) Knights tour is a special case of Hamiltonian Cycle solvable in linear) 

How do we compute a topologically sorted sequence?
In a dag, there must be at least one vertex with no incoming edge, or a vertext with indegree 0.  This gives a Notion of a rank: vertex wiith indegree of 0 have rank 0, and vertices of rank i have an incomiing edge from vertices of rank i-1. This rank produces a topological sort.  computing indegree(v) for all v and doing a bfs will produce topological sort

Applications: Edit ladder

In general, finding the longest path in a graph efficiently is difficult. No algorithm short of brute force enumeration of all paths is known for general graphs. However, this can be done if the graph is a dag. There are no cycles because any edge goes respects the dictionary order in the list of words. 


Longest Path problem:

Graphs without cycles:
Trick: Give every edge a weight  -1, and apply Dijstra's
Directed Graphs: For a dag, we can calculate the longest path by doing a topological sort and finding the maximum rank.




----------


- What type of collection should be used to store each element of adjacency list? One could use a a set, a dynamic array, a linked-list, or a hashtable (which is just an array with object indices).

- Should there be a second adjacency list, inadj, that stores, for each i, the list of vertices, j, such that(j,i) in E$? This can greatly reduce the running-time of the inEdges(i) operation, but requires slightly more work when adding or removing edges.

- Should the entry for the edge i,j in adj[i] be linked by a reference to the corresponding entry in inadj[j]?

- Should edges be first-class objects with their own associated data? In this way, adj would contain lists of edges rather than lists of vertices (integers).

Most of these questions come down to a tradeoff between complexity (and space) of implementation and performance features of the implementation.



Graphs can be represented by:


- Edge Lists:  Here edges are first-class objects with their own associated data. Separate unordered sequence (list, array etc.) of edges holding pointers to vertex objects (which can themselves be held in an unordered sequence).
Space: O(n+m)
Con: 
(1) Difficult to do adjacency queries:
query all the edges adjacent on a given vertex or vertices adjacent to a given vertex or finding degree or whether two givenvertices are adjacent 
(2) Removing vertex: will have to go through all the edges O(m),  


- Adjacency Lists Traditional. Vertices-centred. Array corresponding to vertices holding refernces to linkedlist<vertex>. (We can store additional information about vertices in this array)
Space: O(n + m). 
Pro: Queries about adjacency information can be done in O(deg(v))
Con:  edge information can be stored, but difficult to retrieve (O n+m) Edges are not first-class objects. 

Adjacency Lists Modern: Array corresponding to vertices with pointers to linkedlists of inEdges and outEdges. Both edges and vertices are first-class objects
Space: O(n + m)
Pro: Queries about adjacency information can be done in O(deg(v)) and edge information can be retrieved.
Good for sparse, dynamic graphs where edge information is relevant. 

Adjancency Matrix traditional (a two-dimenstional array of 1 and 0).
Space: O(n^2)
Adjacency Matrix Modern: a two dimensional array of references to unordered sequence of edge. 



Pro: If number of vertices is small compared to number of edges (Dense graph)Adjacency queries take order O(n) now (not O(m)), though individual adjacency between given vertex can be checked in O(1). 

Edge information can be retreived in O(1).
Con: insertion and removal take O(n^2)

Good for dense, static graphs  with small number of vertices where edge information is relevant.

Though the exact implementation might be application dependend. For example, if a very frequent operation you need is to find the vertex with max degree, which would take  O(n) if you maintain degree in adjacency list or matrix, it might be recommended to use the priority queue, which would give you O(1)

https://stackoverflow.com/questions/1945099/java-which-is-the-best-implementation-structure-for-graph



A two-array implementation: A more efficient option is to maintain two linear arrays.

           Offset              Neighbours
       1      1    -------------->  2
       2      3    ------------     3
       3      5    ----------  |->  1
       4      9    --------  |      3
       5     10    ------  | |--->  1
       6     12    ----  | |        2
       7     14    --  | | |        4
                     | | | |        6
                     | | |  ----->  3
                     | |  ------->  6
                     | |            7
                     |  --------->  5
                     |              7
                      ----------->  5
                                    6
      
Offset[i] stores the position in Neighbours where the neighbours of vertex i begin. To examine all neighbours of vertex i, scan Neighbours[Offset[i]], Neighbours[Offset[i]]+1, … Neighbours[Offset[i+1]]-1. however, that adding edges in this representation is complicated.

Another way is to represent the list of neighbours using two arrays as follows. Here, each neighbour points (via "Next") to the next neighbour, until the last neighbour which has Next = -1.

           Offset                 Node  Next
       1      1    -------------->  2    2
       2      3    ------------     3   -1
       3      5    ----------  |->  1    4
       4      9    --------  |      3   -1
       5     10    ------  | |--->  1    6
       6     12    ----  | |        2    7
       7     14    --  | | |        4    8
                     | | | |        6    9
                     | | |  ----->  3   -1
                     | |  ------->  6   11
                     | |            7   -1
                     |  --------->  5   13
                     |              7   -1
                      ----------->  5   15
                                    6   -1





This is an adjacency list implementation. 
 */

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



class Edge<VertexT>{

	VertexT source;
	VertexT destination;
	boolean isDirected;
	int weight;

}


class DegreeVertex {
	int inDegree;
	int outDegree;
	int degree;
	String label;

}


 final class AdjacencyListGraph<V extends DegreeVertex, E extends Edge<V>> implements Graph<V,E>{

	private final Map<V, Set<V>> adjacencyLists; 
	private int edgeCount;
	private final boolean isDirected = false;
	// private final boolean weighted;

	 AdjacencyListGraph(){

		this.adjacencyLists = new HashMap<>();

	}


	public boolean isEmpty(){
		return adjacencyLists.isEmpty();
	}

	public int vertexCount(){
		return adjacencyLists.size(); //To Check: What does it return at the beginning?
	}

	public int edgeCount(){
		return edgeCount;

	}

	public boolean isDirected(){
		return isDirected;
	}

	public boolean addVertex(final V vertex){
		if(adjacencyLists.containsKey(vertex)){
			return false;
		} else {
			adjacencyLists.put(vertex, new HashSet<>());
			return true;
		}

	}



	public boolean addEdge(final E edge){

		V source = (V) edge.source;
		V destination = (V) edge.destination;
		if(!adjacencyLists.containsKey(source) || !adjacencyLists.containsKey(destination)) throw new IllegalArgumentException("Vertices not found");
		Set<V> neighbours = adjacencyLists.get(source); // This cannot be null as we initialize with new HashSet<>().
		edgeCount++;
		return neighbours.add(destination); 

	}


	// Vertex adjacency methods
	
	public Set<V> getNeighbours(final V vertex){

		if(adjacencyLists.containsKey(vertex) == false){
			throw new IllegalArgumentException("Vertex not in Graph");
		} else {
			return adjacencyLists.get(vertex);
		}

	}

	public Set<E> outIncidentEdges(final V vertex){

	}


	public int degree(final V vertex){
		return vertex.degree;
	}


	public int inDegree(final V vertex){

		return vertex.inDegree;

	}

	public int outDegree(final V vertex){

		return vertex.outDegree;
	}

	public boolean isNeighbour(V source, V destination){

		if(!adjacencyLists.containsKey(source) || !adjacencyLists.containsKey(destination)) throw new IllegalArgumentException("Atleast one vertex not found");

		Set<V> neighbours =  adjacencyLists.get(source);
		if(neighbours.contains(destination)){
			return true; 
		} else {
			return false;
		  }

	}


	// Edge related methods.
	// 
	public boolean isDirected(final E edge){
		return edge.isDirected;
	}

	public V origin(final E edge){
		return edge.source;
	}

	public V destination(final E edge){
		return edge.destination;
	}

	public Set<V> endVertices(final E edge){
		Set<V> endVertices = new HashSet<V>();
		endVertices.add(edge.source);
		endVertices.add(edge.destination);

	}

}






