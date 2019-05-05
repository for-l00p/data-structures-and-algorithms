

import java.util.Set;
import java.util.HashSet;

class Edge<V> implements Comparable<Edge<V>>{

	boolean isDirected = false;
	private V source;
	private V destination;
	double weight = 1;

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


	public Edge(V source, V destination, boolean isDirected, double weight){
		this(source, destination);
		this.isDirected = isDirected;
		this.weight = weight;

	}

	public Edge(V source, V destination, double weight){
		this(source, destination);
		this.weight = weight;

	}

	
	@Override
	public int compareTo(Edge<V> edge){

		return Double.compare(this.weight, edge.weight);

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

	public StringBuilder print(){
		StringBuilder s = new StringBuilder();
		if(isDirected){
			s.append(source + " -> " + destination);
		} else {
			s.append(source + " -- " + destination);
		}		
		return s;
	}



}