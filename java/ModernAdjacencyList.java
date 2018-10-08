
/**
 * 
A tree is a connected graph with no cycles. 

A tree with n nodes has n-1 edges (Proof by induction)
If the #Edges < n-1, then the graph cannot be connected (Proof by contradiction with earlier assertion that tree has n-1 edges)

An eulerian tour exists iff only all vertices have even degree. 


----------


- What type of collection should be used to store each element of adjacency list? One could use an array-based list, a linked-list, or even a hashtable. 
What we are looking for: Order is not relevant. Duplicates not allowed. So something implementing set interface (as oppopsed to list would do). 


- Should there be a second adjacency list, inadj, that stores, for each i, the list of vertices, j, such that(j,i) in E$? This can greatly reduce the running-time of the inEdges(i) operation, but requires slightly more work when adding or removing edges.

- Should the entry for the edge i,j in adj[i] be linked by a reference to the corresponding entry in inadj[j]?

- Should edges be first-class objects with their own associated data? In this way, adj would contain lists of edges rather than lists of vertices (integers).


Most of these questions come down to a tradeoff between complexity (and space) of implementation and performance features of the implementation.

- Is the graph directed? Is it weighted?Are Parallel edges and self-loops are permitted?


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

Though the exact implementation might be application dependent. For example, if a very frequent operation you need is to find the vertex with max degree, which would take  O(n) if you maintain degree in adjacency list or matrix, it might be recommended to use the priority queue, which would give you O(1)
https://stackoverflow.com/questions/1945099/java-which-is-the-best-implementation-structure-for-graph

 */



/**


This is a modern adjacency list implementation of a graph. The graph could be directed or undirect; the edges could be weighted or unweighted, and the vertice can correspond to any generic type (e.g. String or Integer).

We use a Map to hold each vertex's adjacency information. 
The adjacency information contains:
- the adjacency 'list' outEdges:  a set that stores, for each vertex i, the list of vertices, j, such that(i, j) belong to E.  
- a second adjacency list, inEdges, that stores, for each i, the list of vertices, j, such that(j,i) in E.  This can greatly reduce the running-time of the inIncidentEdges(i) operation, but requires slightly more work when adding or removing edges. 
- a count of the vertex's indegree and outdegree. This allows us to  get degree information in O(1) time. 




**/

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;



interface Graph<V,E>{

	/**
	 * The interface body can contain abstract methods, default methods, and static methods. Default methods are defined with the default modifier, and static methods with the static keyword. All abstract, default, and static methods in an interface are implicitly public, so you can omit the public modifier.
	 * 
	 * In addition, an interface can contain constant declarations. All constant values defined in an interface are implicitly public, static, and final. Once again, you can omit these modifiers.
	 * @return [description]
	 */

	// Basic container methods 
	//int size();
	boolean isEmpty();
	//void replaceElement(V vertex);
	//void swap(V vertex1, V vertex2);
	int vertexCount();
	int edgeCount();
	Set<E> getEdges();
	Set<V> getVertices();


	//container modification methods
	boolean addVertex(V vertex);
	boolean addEdge(V source, V destination);
	//  void addDirectedEdge(E edge);
	V removeVertex(V vertex);
	E removeEdge(V source, V destination);

	// void setRoot(V vertex);

	//  void hasCycle();
	//  void isConnected();
	// makeUndirected();
	// reverseDirection();

	//Vertex Adjacency methods
	Set<V> getNeighbours(V vertex);
	Set<E> inIncidentEdges(V vertex);
	Set<E> outIncidentEdges(V vertex);
	boolean isNeighbour(V vertex1, V vertex2);
	int degree(V vertex);
	int inDegree(V vertex);
	int outDegree(V vertex);




}


class Edge<V>{

	boolean isDirected = false;
	private V source;
	private V destination;
	int weight = 1;

	public Edge(){

	}

	public Edge(V source, V destination){
		this.source = source;
		this.destination = destination;
		this.isDirected = false;
		this.weight = 1;

	}

		public Edge(V source, V destination, boolean isDirected){
		this(source, destination);
		this.isDirected = isDirected;
	}


	public Edge(V source, V destination, boolean isDirected, int weight){
		this(source, destination);
		this.isDirected = isDirected;
		this.weight = weight;

	}

	public boolean isDirected(){
		return isDirected;
	}

	public boolean contains(final V vertex){
		if (source == vertex || destination == vertex){
			return true;
		} else {
			return false;
		}
	}

	public V source(){
		return source;
	}

	public V destination(){
		return destination;
	}

	public V opposite(final V vertex){
		return (source == vertex) ? destination: source;
	}

	public Set<V> endVertices(){
		Set<V> endVertices = new HashSet<V>();
		endVertices.add(source);
		endVertices.add(destination);
		return endVertices;
	}



}


class AdjacencyInfo<V>{

	int inDegree;
	int outDegree;
	Set<Edge<V>> outEdges; 
	Set<Edge<V>> inEdges ;
	boolean isVisited;

	public AdjacencyInfo(){
		this.inDegree = 0;
		this.outDegree = 0;
		this.outEdges = new HashSet<>();
		this.inEdges = new HashSet<>();
	}



}
/**
 * Why final? One should make your class final unless you're explicitly intending it to be extended.
 */
 final class ModernAdjacencyList<V> implements Graph<V,Edge<V>>{

	private Map<V,AdjacencyInfo<V>> vertices;

	/**
	 * Why Map? It is preferred to use the interface as the type rather than the implementation. This makes it easier to change implementations in the future. Both because you specify the implementation in fewer places and because this forces you to code to the interface. Within our code, the fact that vertices is a HashMap and not a TreeMap is irrelevant. So just refer to it as a Map.  
	 *
	 * Why Private? You don't want other classes in your package reaching into a this class and messing with its adjacencies. 
	 *
	 * Should we also make it Final?  
	 */

	private boolean isGraphDirected;
	private int edgeCount;
	private boolean weighted;

	public ModernAdjacencyList(){

		this.vertices = new HashMap<>();
		this.edgeCount = 0;
		this.isGraphDirected = false;
	}

	public ModernAdjacencyList(boolean directed){

		this();
		this.isGraphDirected = directed;

	}

	public ModernAdjacencyList(boolean directed, boolean weighted){

		this(directed);
		this.weighted = weighted;
		
	}

	public boolean isEmpty(){
		return vertices.isEmpty();
	}


	public int vertexCount(){
		return vertices.size(); 
	}


	public int edgeCount(){
		return edgeCount;

	}

	public boolean isDirected(){
		return isGraphDirected;
	}



	public Set<V> getVertices(){
		return vertices.keySet();
	}

	public Set<Edge<V>> getEdges(){

		Set<Edge<V>> edges = new HashSet<>();
		for(V vertex: vertices.keySet()){
			Set<Edge<V>> outEdges = outIncidentEdges(vertex);
			for(Edge<V> edge: outEdges){
				edges.add(edge);
			}
		}
		return edges;
	}


 /*************************************************************************
     					Graph Modification Methods
   ***************************************************************************/

	public boolean addVertex(V vertex){

		if(vertices.containsKey(vertex)){
			return false;
		} else {
			vertices.put(vertex, new AdjacencyInfo<V>());
			return true;
		}

	}


/**
 * The source and destination must belong to the graph already. 


 * If the graph is undirected, we add the edge to both the vertices' outedges. Thus, for outedges, the terms source and destination are meaningless: vertex v's outedges might contain the vertex v as a destination. 

 * If the graph is directed, we add the edge to the source's outedges and the destination's inedges. Here source and destination correspond to the direct of the edge.

 * @param  edge the edge object to be added ()
 * @return true if the edge is added to the respective vertices' adjacency list, false if the edge was already present in atleast one vertex's adjacency list. 
 * 
 */

	public boolean addEdge(V source, V destination){

		if(!vertices.containsKey(source) || !vertices.containsKey(destination)) throw new IllegalArgumentException("Atleast one vertex is not present in Graph");

		Set<Edge<V>> destinationEdges;
		if(isGraphDirected){
			destinationEdges = inIncidentEdges(destination);
		} else {
			destinationEdges = outIncidentEdges(destination);
		}

		Set<Edge<V>> sourceEdges = outIncidentEdges(source); 

		Edge<V> edge;
		if(isGraphDirected){
			 edge = new Edge<>(source, destination, true);
		} else {
			 edge = new Edge<>(source, destination);
		}

		if((sourceEdges.add(edge) && destinationEdges.add(edge))){
			edgeCount++;
			return true;
		} else {
			System.out.println("Edge addition failed");
			return false;
		}
		
	}

/**
 * In an undirected graph, removes edges containing the vertex from its neighbours. The vertex might be in source or destination of an edge. 

 * @param  vertex to be removed
 * @return  vertex the removed vertex
 */
	public V removeVertex(V vertex){

		if(!vertices.containsKey(vertex)){
			throw new IllegalArgumentException("Vertex not in Graph");
		}

		
		
		Set<Edge<V>> outEdges= outIncidentEdges(vertex);

		for(Edge<V> edge: outEdges){
			V neighbour = edge.opposite(vertex);
			Set<Edge<V>> neighbourEdges;
			if(isGraphDirected){
				neighbourEdges = inIncidentEdges(neighbour);
			} else {
				neighbourEdges = outIncidentEdges(neighbour);
			}
			for(Edge<V> neighbourEdge: neighbourEdges){
				if (neighbourEdge.contains(vertex)){
					neighbourEdges.remove(neighbourEdge);
					// break; if parallel edges are not allowed
				}
			}
		}

		if(isGraphDirected){

			Set<Edge<V>> inEdges= inIncidentEdges(vertex);
			for (Edge<V> inEdge: inEdges){
				V inNeighbour = inEdge.source();
				Set<Edge<V>> inNeighbourOutEdges = outIncidentEdges(inNeighbour);
				for(Edge<V> inNeighbourOutEdge: inNeighbourOutEdges){
					if (inNeighbourOutEdge.contains(vertex)){
						inNeighbourOutEdges.remove(inNeighbourOutEdge);
					// break; (if parallel edges are not allowed)
					}
				}
			}
		} 
		
		vertices.remove(vertex);
		return vertex;

	}




	public Edge<V> removeEdge(V source, V destination){

		if(!vertices.containsKey(source) || !vertices.containsKey(destination)) throw new IllegalArgumentException("Vertices not found");


		Set<Edge<V>> sourceOutEdges = vertices.get(source).outEdges;

		Edge<V> edgeInSource = null;
		for(Edge<V> edge: sourceOutEdges) {
			if (edge.contains(destination)){
				edgeInSource = edge;
				break;
			}
		}
		sourceOutEdges.remove(edgeInSource);


		Set<Edge<V>> destinationEdges;	
		Edge<V> edgeInDestination = null;

		if(isGraphDirected){
			destinationEdges = vertices.get(destination).inEdges;
		} else {
			destinationEdges = vertices.get(destination).outEdges;
		}

		for(Edge<V> edge: destinationEdges) {
			if (edge.contains(source)){
				edgeInDestination = edge;
				break;
			}
		}

		destinationEdges.remove(edgeInDestination);	

		
		return edgeInSource;
	}



 /*************************************************************************
     					Vertex adjacency methods
   ***************************************************************************/
	
	
	public Set<V> getNeighbours(final V vertex){

		if(!vertices.containsKey(vertex)){
			throw new IllegalArgumentException("Vertex not in Graph");
		} 

		Set<Edge<V>> outEdges = vertices.get(vertex).outEdges;
		Set<V> neighbours = new HashSet<>();
		for(Edge<V> edge: outEdges){
			neighbours.add(edge.opposite(vertex));
		}
		return neighbours;
		

	}


	public Set<Edge<V>> inIncidentEdges(final V vertex){
		if(!vertices.containsKey(vertex)){
			throw new IllegalArgumentException("Vertex not in Graph");
		}

		Set<Edge<V>> inEdges = vertices.get(vertex).inEdges;
		return inEdges;
	}

	public Set<Edge<V>> outIncidentEdges(final V vertex){

		if(!vertices.containsKey(vertex)){
			throw new IllegalArgumentException("Vertex not in Graph");
		}

		Set<Edge<V>> outEdges = vertices.get(vertex).outEdges;
		return outEdges;
	}


	public int degree(final V vertex){

		if(!vertices.containsKey(vertex)){
			throw new IllegalArgumentException("Vertex not in Graph");
		}

		return vertices.get(vertex).outEdges.size();
	}

	public int inDegree(final V vertex){

		if(vertices.containsKey(vertex) == false){
			throw new IllegalArgumentException("Vertex not in Graph");
		}

		return vertices.get(vertex).inEdges.size();

	}

	public int outDegree(final V vertex){

		if(vertices.containsKey(vertex) == false){
			throw new IllegalArgumentException("Vertex not in Graph");
		}

		return vertices.get(vertex).outEdges.size();
	}

	public boolean isNeighbour(V source, V destination){

		if(!vertices.containsKey(source) || !vertices.containsKey(destination)) throw new IllegalArgumentException("Atleast one vertex not found");

		Set<Edge<V>> outEdges = vertices.get(source).outEdges;
		for(Edge<V> edge: outEdges){
			if(edge.contains(destination)){
				return true;
			}
		}
		return false;

	}


/**
 * Unit tests the BST data structure
 * @param args the command-line arguments

 */

	public static void main(String[] args){

		ModernAdjacencyList<String> test = new ModernAdjacencyList<>();
		test.addVertex("Delhi");
		System.out.println(test.vertexCount());
		System.out.println(test.edgeCount());
		


	}

}







