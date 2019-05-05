import java.util.*;

class Solution {
    public static boolean isBipartite(int[][] graph) {
        
        int n = graph.length;
       
        int[] visited = new int[n];
        int[] distance = new int[n];
        boolean isBipartite = true;
        for (int i = 0; i < n; i++){
            if (visited[i] == 0){
                isBipartite = bfs(graph, i, visited, distance);
            }
        }
        return isBipartite;
        
    }
    
    private static boolean bfs(int[][] graph, int source, int[] visited, int[] distance){
        int n = graph[0].length;
        distance[source] = 0;
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.add(source);
        visited[source] = 1;
        boolean isBipartite = true;
        while(!bfsQueue.isEmpty()){
            int current = bfsQueue.poll();
            System.out.println("At node: " + current);
            for (int i = 0; i < graph[current].length; i++){
                int neighbour = graph[current][i];
    
                    System.out.println("Investigating neighbour: " + neighbour);
                    if(visited[neighbour] == 0){
                        distance[neighbour] = distance[current] + 1;
                         System.out.println("Assgnng distance: " + distance[neighbour]);
                        visited[neighbour] = 1;
                        bfsQueue.add(neighbour);
                    } else if (visited[neighbour] == 1){
                        System.out.println("already visited " + distance[neighbour] + " " + distance[current]);
                        if ((distance[neighbour] + distance[current]) % 2 == 0){
                            isBipartite = false;
                        }
                    }
                    
                    
                
            }
        }
        return isBipartite;
        
        
    }

    public static void main(String[] args){
        int[] row0 = new int[]{1,2,3};
        int[] row1 = new int[]{0,2};
        int[] row2 = new int[]{0,1,3};
        int[] row3 = new int[]{0,2};
        int[][] graph = {row0,row1,row2,row3};
        System.out.println(isBipartite(graph));

    }
}