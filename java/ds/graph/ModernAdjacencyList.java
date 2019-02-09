
/**
 *
 *
 * Sources: https://algs4.cs.princeton.edu/41graph/
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

- Should edges be first-class objects with their own associated data? If yes, adjacency list would contain lists of edges rather than lists of vertices (integers).


Most of these questions come down to a tradeoff between complexity (and space) of implementation and performance features of the implementation.

- Is the graph directed? Is it weighted? (Can be represented by vertex, weight pairs in the adjacency list). Are Parallel edges and self-loops are permitted? Is the graph connected? 


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


There isn’t one. Seriously. There’s never a “best” data structure for representing something as general as “a graph”.

Graphs can be represented by matrices, or lists, or sets, or heaps, or queues, or hash tables, or trees, or tries, or a distributed table of heaps of lists of pointers to vectors with twenty ancillary hash tables.

How many vertices are you expecting to hold? 100? 10,000? 10,000,000,000? How many edges, or what’s the expected average degree? What sort of things do you need to do with the graph? Does it frequently update? Do you need to calculate connected components? 𝑘-connected components? Planar embeddings? Independent sets? Colorings? Eigenvalues? Flows? Paths? Local features? Global features? How quickly? How often? How accurately? How reliably?

Once you have an idea about the answers to those questions you can start evaluating possible data structures and implementations. “A graph” really says nothing at all about what you’re trying to achieve, and “best data structure” is almost never a meaningful concept.

Adjacency matrices are pretty good for dense, directed graphs. They’re very compact with only one bit per edge (if you do it right). But if the graph is undirected then the matrix is redundant (it’s symmetric) so we could save half the space. If the graph is bipartite then that’s even worse.

For sparse graphs, it is usual to either have an adjacency list per node, or to store just the edges in a lookup structure such as a hash table. But some graph algorithms become inefficient or have to be rewritten to work well on a sparse graph representation. For example, the scipy.sparse matrix package has seven different sparse matrix representations. (It typically recommends compressed sparse column/row formats for graph algorithms, but the others may be better for I/O or storage.)

. In any case, you will want to encapsulate all this in primitives such as ListNeighbors(v) and IsAdjacent(u,v) that you can use to implement various graph algorithms.

https://www.quora.com/What-is-the-best-data-structure-for-representing-a-graph-in-computer-science
 */



/**


This is a modern adjacency list implementation of a graph. The graph could be directed or undirected; the edges could be weighted or unweighted, and the vertice can correspond to any generic type (e.g. String or Integer).

We use a Map to hold each vertex's adjacency information. 
The adjacency information contains:
- the adjacency 'list' outEdges:  a set that stores, for each vertex i, the list of vertices, j, such that(i, j) belong to E.  
- a second adjacency list, inEdges, that stores, for each i, the list of vertices, j, such that(j,i) in E.  This can greatly reduce the running-time of the inIncidentEdges(i) operation, but requires slightly more work when adding or removing edges. 
- a count of the vertex's indegree and outdegree. This allows us to  get degree information in O(1) time. 

If the graph is undirected, the inEdges information is useless. 


**/

/******************************************************************************
 *  Compilation:  javac ModernAdjacencyList.java
 *  Execution:    java ModernAdjacencyList
 *  Dependencies: Graph.java Edge.java
 *  Data files:   
 *
 *

 *
 ******************************************************************************/


import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Why final? One should make your class final unless you're explicitly intending it to be extended.
 */


 final class ModernAdjacencyList<V> implements Graph<V,Edge<V>>{



 /*************************************************************************
     					State Variables
   ***************************************************************************/
	

	private Map<V,AdjacencyInfo<V>> vertices;
	private int edgeCount;
	private boolean weighted;
	private boolean isGraphDirected;


	/**
	 * Why Map? It is preferred to use the interface as the type rather than the implementation. This makes it easier to change implementations in the future. Both because you specify the implementation in fewer places and because this forces you to code to the interface. Within our code, the fact that vertices is a HashMap and not a TreeMap is irrelevant. So just refer to it as a Map.  
	 *
	 * Why Private? You don't want other classes in your package reaching into a this class and messing with its adjacencies. 
	 *
	 * Should we also make it Final?  
	 */

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



 /*************************************************************************
     					Constructors
   ***************************************************************************/
	


	public ModernAdjacencyList(){

		this.vertices = new HashMap<V, AdjacencyInfo<V>>(); // Shouldn't use empty diamond  UNLESS you are defining AND instantiating on the same line. Map<V, AdjacencyInfo<V>> vertices = new HashMap<>() is OK, but if you define private AdjacencyInfo<V>> vertices, and then halfway down the page you instantiate with vertices = new new HashMap<>(), then it's not cool! What does vertices contain again? Oh, let me hunt around for the definition. Suddenly, the benefit of the diamond shortcut (where types are infered to reduce verbosity) goes bye bye.

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





 /*************************************************************************
     					Container Info Methods
   ***************************************************************************/
	


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
		return new HashSet<V>(vertices.keySet());
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



	public String toString(){

		String NEWLINE = System.getProperty("line.separator");
		StringBuilder s = new StringBuilder();
		for(Map.Entry<V, AdjacencyInfo<V>> vertex : vertices.entrySet()){
			s.append(vertex.getKey() + NEWLINE);
			AdjacencyInfo<V> adjacencyInfo = vertex.getValue();
			s.append("outEdges: ");	
			for(Edge<V> edge: adjacencyInfo.outEdges){
				s.append(edge.source());
				if(weighted){
					s.append("-" + edge.weight+ "-");
				} else {
					s.append("-" + "-");
				}
				
				s.append(edge.destination());
				s.append(", ");
			}
			if(isGraphDirected){
				s.append(NEWLINE);
				s.append("inEdges: ");	
				for(Edge<V> edge: adjacencyInfo.inEdges){
					s.append(edge.source());
					s.append("--> ");
					s.append(edge.destination());
					s.append(", ");
				}
			}
			
			s.append(NEWLINE);
			s.append(NEWLINE);

		}

		return s.toString();

	}



		
	private void validateVertex(V vertex){
		if(!vertices.containsKey(vertex)) throw new IllegalArgumentException("Vertex not present in Graph:" + vertex);
	}

 /*************************************************************************
     					Graph Modification Methods
   ***************************************************************************/




/**
 * The source and destination must belong to the graph already. 


 * If the graph is undirected, we add the edge to both the vertices' outedges. Thus, for outedges, the terms source and destination are meaningless: vertex v's outedges might contain the vertex v as a destination. 

 * If the graph is directed, we add the edge to the source's outedges and the destination's inedges. Here source and destination correspond to the direct of the edge.

 * @param  edge the edge object to be added ()
 * @return true if the edge is added to the respective vertices' adjacency list, false if the edge was already present in atleast one vertex's adjacency list. 
 * 
 */

	public boolean addEdge(V source, V destination){

		validateVertex(source);
		validateVertex(destination);
	
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
			System.out.println("Possible failed edge addition " + edge.print());
			return false;
		}
		
	}

	public boolean addEdge(V source, V destination, double weight){

		validateVertex(source);
		validateVertex(destination);
	
		Set<Edge<V>> destinationEdges;
		if(isGraphDirected){
			destinationEdges = inIncidentEdges(destination);
		} else {
			destinationEdges = outIncidentEdges(destination);
		}

		Set<Edge<V>> sourceEdges = outIncidentEdges(source); 

		Edge<V> edge;
		if(isGraphDirected){
			 edge = new Edge<>(source, destination, true, weight);
		} else {
			 edge = new Edge<>(source, destination, weight);
		}

		if((sourceEdges.add(edge) && destinationEdges.add(edge))){
			edgeCount++;
			return true;
		} else {
			if(sourceEdges == destinationEdges){
				return true;
			} else {
				System.out.println("Possible failed edge addition " + edge.print());
				return false;
			}
			
		}
		
	}




/**
 * In an undirected graph, removes edges containing the vertex from its neighbours. The vertex might be in source or destination of an edge. 

 * @param  vertex to be removed
 * @return  vertex the removed vertex
 */


	public void removeEdge(V source, V destination){
		validateVertex(source);
		validateVertex(destination);

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

		
		//return edgeInSource;
	}



	public boolean addVertex(V vertex){

		if(vertices.containsKey(vertex)){
			return false;
		} else {
			vertices.put(vertex, new AdjacencyInfo<V>());
			return true;
		}

	}

	public void removeVertex(V vertex){

		validateVertex(vertex);
		
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
		//return vertex;

	}






 /*************************************************************************
     					Vertex adjacency methods
   ***************************************************************************/
	

// Remarkably, we can build all of the algorithms that we consider in this section on the basic abstraction embodied in getNeighbours.

	public Set<V> getNeighbours(final V vertex){

		validateVertex(vertex);
		Set<Edge<V>> outEdges = vertices.get(vertex).outEdges;
		Set<V> neighbours = new HashSet<>();
		for(Edge<V> edge: outEdges){
			neighbours.add(edge.opposite(vertex));
		}
		return neighbours;
		

	}


	public Set<Edge<V>> inIncidentEdges(final V vertex){

		validateVertex(vertex);
		Set<Edge<V>> inEdges = vertices.get(vertex).inEdges;
		return inEdges;
	}

	public Set<Edge<V>> outIncidentEdges(final V vertex){

		validateVertex(vertex);
		Set<Edge<V>> outEdges = vertices.get(vertex).outEdges;
		return outEdges;
	}


	public int degree(final V vertex){

		validateVertex(vertex);
		return vertices.get(vertex).outEdges.size();
	}

	public int inDegree(final V vertex){

		validateVertex(vertex);
		return vertices.get(vertex).inEdges.size();

	}

	public int outDegree(final V vertex){

		validateVertex(vertex);
		return vertices.get(vertex).outEdges.size();
	}

	public boolean isNeighbour(V source, V destination){

		validateVertex(source);
		validateVertex(destination);

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

	  Graph<String, Edge<String>> testGraph = new ModernAdjacencyList<>(true);
      testGraph.addVertex("Delhi");
      testGraph.addVertex("Gurgaon");
      testGraph.addVertex("Chandigarh");
      testGraph.addVertex("Bangalore");
      testGraph.addVertex("Mumbai");
      testGraph.addVertex("London");
      testGraph.addVertex("Noida");
      testGraph.addVertex("Singapore");
      testGraph.addVertex("Thailand");
      testGraph.addVertex("Indonesia");
      testGraph.addEdge("Indonesia", "Thailand");
      testGraph.addEdge("Indonesia", "Noida");
      testGraph.addEdge("Delhi", "Noida");
      testGraph.addEdge("Delhi", "Mumbai");
      testGraph.addEdge("London", "Thailand");
      testGraph.addEdge("Mumbai", "Thailand");
      testGraph.addEdge("Chandigarh", "Gurgaon");
      testGraph.addEdge("Thailand", "Gurgaon");
      testGraph.addEdge("London", "Chandigarh");
      testGraph.addEdge("Noida", "Chandigarh");
      testGraph.addEdge("Chandigarh", "Delhi");
      testGraph.addEdge("Delhi", "Singapore");
      testGraph.addEdge("Indonesia", "Singapore");
      System.out.println(testGraph.vertexCount());
      System.out.println(testGraph.edgeCount());
      System.out.println(testGraph.toString());




	}

}







