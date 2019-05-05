import java.util.Set;

interface Graph<V,E>{

	/**
	 * 
	 * An interface can contain constant declarations. All constant values defined in an interface are implicitly public, static, and final. 

	 */

	// Basic container methods 
	//int size();
	//boolean isEmpty();
	//void replaceElement(V vertex);
	//void swap(V vertex1, V vertex2);
	int vertexCount();
	int edgeCount();
	//Set<E> getEdges();
	Iterable<V> getVertices();


	//container modification methods
	boolean addVertex(V vertex);
	boolean addEdge(V source, V destination);
	//  void addDirectedEdge(E edge);
	//void removeVertex(V vertex);
	void removeEdge(V source, V destination);

	// void setRoot(V vertex);

	//  void hasCycle();
	//  void isConnected();
	// makeUndirected();
	// reverseDirection();

	//Vertex Adjacency methods
	Iterable<V> getNeighbours(V vertex);
	//Set<E> inIncidentEdges(V vertex);
	//Iterable<E> outIncidentEdges(V vertex);
	boolean isNeighbour(V vertex1, V vertex2);
	//int degree(V vertex);
	//int inDegree(V vertex);
	//int outDegree(V vertex);


}