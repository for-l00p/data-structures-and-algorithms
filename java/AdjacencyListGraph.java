
/**
 * 
A tree is a connected graph with no cycles. 

A tree with n nodes has n-1 edges (Proof by induction)
If the #Edges < n-1, then the graph cannot be connected (Proof by contradiction with earlier assertion that tree has n-1 edges)

An eulerian tour exists iff only all vertices have even degree. 


----------


- What type of collection should be used to store each element of adjacency list? One could use an array-based list, a linked-list, or even a hashtable.

- Should there be a second adjacency list, inadj, that stores, for each i, the list of vertices, j, such that(j,i) in E$? This can greatly reduce the running-time of the inEdges(i) operation, but requires slightly more work when adding or removing edges.

- Should the entry for the edge i,j in adj[i] be linked by a reference to the corresponding entry in inadj[j]?

- Should edges be first-class objects with their own associated data? In this way, adj would contain lists of edges rather than lists of vertices (integers).

Most of these questions come down to a tradeoff between complexity (and space) of implementation and performance features of the implementation.



Graphs can be represented by:

Edge Lists:  Here edges are first-class objects with their own associated data. Separate unordered sequence (list, array etc.) of edges holding pointers to vertex objects (which can themselves be held in an unordered sequence).
Space: O(n+m)
Con: (1) Difficult to do adjacency quries:
query all the edges adjacent on a given vertex or vertices adjacent to a given vertex or finding degree or whether two givenvertices are adjacent 
(2) Removing vertex: will have to go through all the edges O(m),  


Adjacency Lists Traditional. Vertices-centred. Array corrsponding to vertices holding refernces to linkedlist<vertex>. (We can store additional information about vertices in this array)
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

Though the exact implementation might be application dependendt. For example, if a very frequent operation you need is to find the vertex with max degree, which would take  O(n) if you maintain degree in adjacency list or matrix, it might be recommended to use the priority queue, which would give you O(1)
https://stackoverflow.com/questions/1945099/java-which-is-the-best-implementation-structure-for-graph




This is an adjacency list implementation. 

 there are many different choices to be made when implementing a graph as an adjacency list. Some questions that come up include:



 */

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
// import java.util.IllegalArgumentException;

// You should make your class final unless you're explicitly intending it to be extended.
 final class AdjacencyListGraph<V extends DegreeVertex,E extends Edge<V>> implements Graph<V,E>{

	private Map<V, Set<V>> adjacencyLists; //. You don't want other classes in your package reaching into a this class and messing with its adjacencies. It would also be reasonable to make it final. Map defines a clear method you can use.

	// In general, prefer using interfaces where possible. Within your code, the fact that adjList is a HashMap and not a TreeMap is irrelevant. So just refer to it as a Map. As a general rule, it is preferred to use the interface as the type rather than the implementation. This makes it easier to change implementations in the future. Both because you specify the implementation in fewer places and because this forces you to code to the interface.

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

interface Graph<V,E>{


	/**
	 * The interface body can contain abstract methods, default methods, and static methods. Default methods are defined with the default modifier, and static methods with the static keyword. All abstract, default, and static methods in an interface are implicitly public, so you can omit the public modifier.
	 * 
	 * In addition, an interface can contain constant declarations. All constant values defined in an interface are implicitly public, static, and final. Once again, you can omit these modifiers.
	 * @return [description]
	 */

	// Basic container methods 
	int size();
	boolean isEmpty();
	void replaceElement(V vertex);
	void swap(V vertex1, V vertex2);
	int numVertices();
	int numEdges();
	Set<E> getEdges();
	Set<V> getVertices();


	//container modification methods
	boolean addVertex(V vertex);
	boolean addEdge(E edge);
	//  void addDirectedEdge(E edge);
	void removeVertex(V vertex);
	void removedEdge(E edge);

	void setRoot(V vertex);

	//  void hasCycle();
	//  void isConnected();
	// makeUndirected();
	// reverseDirection();

	//Vertex Adjacency methods
	Set<V> getNeighbours(V vertex);
	Set<E> getIncidentEdges(V vertex);
	Set<E> inIncidentEdges(V vertex);
	Set<E> outIncidentEdges(V vertex);
	boolean isNeighbour(V vertex1, V vertex2);
	int degree(V vertex);
	int inDegree(V vertex);
	int outDegree(V vertex);

	//Edge related methods
	Set<V> endVertices(E edge);

	V origin(E edge);
	V destination(E edge);
    // V opposite(E edge);
    boolean isDirected(E edge);


}


class Edge<VertexT>{

	VertexT source;
	VertexT destination;
	boolean isDirected;
	int weight;

}


class DegreeVertex{
	int inDegree;
	int outDegree;
	int degree;
	String label;

}




