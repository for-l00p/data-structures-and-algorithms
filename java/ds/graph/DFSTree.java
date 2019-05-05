
/******************************************************************************
 *  Compilation:  javac BFSTree.java
 *  Execution:    java BFSTree
 *  Dependencies: Graph.java 
 *  Data files:   
 *
 *  Run depth first search on a graph.
 *  Runs in O(E + V) time.

 *
 ******************************************************************************/


/**
 
https://www.geeksforgeeks.org/bridge-in-a-graph/

O(V + E) (linear)running time for finding if a graph is 2 connected (articulation edges) 

A DFS only has tree edges and back edges. If there are no back edges, there are just tree edges, implying that there is no cycle. 

In a directed graph, the edges are DFS tree edges, back edges, cross edges and forward edges. 

IMPORTANT PROOF TECHNIQUE: To prove there is no cycle in the graph, prove the existence of an ordering of vertices where all edges are forward. e.g. Order the vertices in decreasing departure times of their BFS.   Forward, tree and cross edges all go from left to right. Hence if there is no back edge, there cannot be a cycle. 

Technique: Reverse Edges in a DAG.
https://stackoverflow.com/questions/5278580/non-recursive-depth-first-search-algorithm
 
*/


import java.util.*;

public class DFSTree<T>{

	  //private Stack<T> dfsStack; 
   	 Map<T,Integer> visitedMap;
   	 Map<T,Integer> arrivalMap;
     Map<T,Integer> departureMap;
   	 Map<T,T> parentsMap;
      int time;
     Map<T,Integer> bridges;
     Map<Integer, Stack<T>> cycles;
     int cycleCount;

   // private boolean isBipartite;


      public DFSTree(){

      //this.dfsStack = new LinkedList<T>();
      this.visitedMap = new HashMap<T,Integer>();
      this.arrivalMap = new HashMap<T,Integer>();
      this.departureMap = new HashMap<T,Integer>();
      this.parentsMap = new HashMap<T,T>();
      this.time = 0;
      this.bridges = new HashMap<T,Integer>();
      this.cycles = new HashMap<Integer, Stack<T>>();
      this.cycleCount = 0;
      
    }

  	public DFSTree(Graph<T, ?> graph, T source){


     // this.dfsStack = new LinkedList<T>();
      this();

      for(T vertex: graph.getVertices()){
        visitedMap.put(vertex,0);
      }
      
        traverseDFS(graph, source);
        bridges.remove(source);
       
    }

    public DFSTree(Graph<T, ?> graph){
     // this.dfsStack = new LinkedList<T>();
        this();

        for(T vertex: graph.getVertices()){
         visitedMap.put(vertex,0);
       }
  
      for(Map.Entry<T, Integer> entry: visitedMap.entrySet()){
        if(entry.getValue() == 0){
          T source = entry.getKey();
          traverseDFS(graph, source);
          bridges.remove(source);
        }
      }
      
     

      for(Map.Entry<T,Integer> item: bridges.entrySet()){
        System.out.println("Bridge Found: " + item.getKey() + " with back edge to " + item.getValue());
      }

  	}

	
	/*************************************************************************
     					Traversals
   ***************************************************************************/
	
   private int traverseDFS(Graph<T, ?> graph, T source){

      time++;
      int distantBackEdge = time;
      System.out.println("Visited:" + source + " at time " + time);
      visitedMap.put(source,1);
      arrivalMap.put(source, time);
      for(T neighour: graph.getNeighbours(source)){
        if(visitedMap.get(neighour) == 0){
          parentsMap.put(neighour, source);
          distantBackEdge = Math.min(distantBackEdge, traverseDFS(graph, neighour));
        } else {
            if(parentsMap.get(source) == neighour){
             continue;
            } else {
              if(cycles.isEmpty()){
                  cycleCount++;
                  //System.out.println("Cycle found with edge between " + source + " and " + neighour);
                  Stack<T>  thisCycle = new Stack<T>();
                  T temp = source;
                  while(!temp.equals(neighour) && temp != null){
                   System.out.println("Pushing on stack: " + temp);
                    thisCycle.push(temp);
                    //if (temp == null) break;
                    temp = parentsMap.get(temp);
                 }
                
              thisCycle.push(temp);

               this.cycles.put(cycleCount, thisCycle);
              }
               
               distantBackEdge = Math.min(distantBackEdge, arrivalMap.get(neighour));
            }
          }
      }
      time++;
      System.out.println("Departed:" + source + " at time " + time);
      departureMap.put(source, time);
      if(distantBackEdge >= arrivalMap.get(source)){
        bridges.put(source, distantBackEdge);
        //System.out.println("Bridge Found: " + source + " with back edge to " + distantBackEdge);
      }
      //System.out.println("distantBackEdge for:  " + source + " is " + distantBackEdge);
      return distantBackEdge;

   }



   public boolean is2C(){

    return this.bridges.isEmpty() && isConnected();

   }

   public boolean isConnected(){
      for(Map.Entry<T, Integer> entry: visitedMap.entrySet()){
        if(entry.getValue() == 0){
          System.out.println("Not present in the BFS Tree:" + entry.getKey());
        }
      }
      return (!visitedMap.containsValue(0));
   }

   public void printCycle(){

    for(Map.Entry<Integer, Stack<T>> item : cycles.entrySet()){
      System.out.println("Cycle Number: " + item.getKey());
      Stack<T> thisCycle = item.getValue();
      StringBuilder s = new StringBuilder();
      for(T v: thisCycle){
        s.append(v + "-->");
      }

      System.out.println(s);
    }
  }

  
   public void printVisitedMap(){
      StringBuilder s = new StringBuilder(); 
      for(Map.Entry<T, Integer>  entry: visitedMap.entrySet()){
        s.append(entry.getKey() + ":" + entry.getValue() + " ");
      }
      System.out.println(s);
   }

   public void printDepartureMap(){
      StringBuilder s = new StringBuilder(); 
      for(Map.Entry<T, Integer>  entry: departureMap.entrySet()){
        s.append(entry.getKey() + ":" + entry.getValue() + " ");
      }
      System.out.println(s);
   }


   public void printArrivalMap(){
      StringBuilder s = new StringBuilder(); 
      for(Map.Entry<T, Integer>  entry: arrivalMap.entrySet()){
        s.append(entry.getKey() + ":" + entry.getValue() + " ");
      }
      System.out.println(s);
   }

   public void printParentsMap(){
      StringBuilder s = new StringBuilder(); 
      for(Map.Entry<T, T>  entry: parentsMap.entrySet()){
        s.append(entry.getKey() + ":" + entry.getValue() + " ");
      }
      System.out.println(s);
   }

    public void printBridges(){
      StringBuilder s = new StringBuilder(); 
      for(Map.Entry<T, Integer>  entry: bridges.entrySet()){
        s.append(entry.getKey() + ":" + entry.getValue() + " ");
      }
      System.out.println(s);
   }


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
      DFSTree<String> testDFS = new DFSTree<>(testGraph, "Chandigarh");
      System.out.println(testDFS.is2C());
      testDFS.printCycle();

  }
}