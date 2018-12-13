import java.util.Set;

interface Graph<V,E>{

	/**
	 * The interface body can contain abstract methods, default methods, and static methods. Default methods are defined with the default modifier, and static methods with the static keyword. All abstract, default, and static methods in an interface are implicitly public, so you can omit the public modifier.
	 * 
	 * In addition, an interface can contain constant declarations. All constant values defined in an interface are implicitly public, static, and final. Once again, you can omit these modifiers.
	 * @return [description]
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