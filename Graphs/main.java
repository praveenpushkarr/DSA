package Graphs;

import java.util.*;
import java.io.*;
// visual representation of graph that we gonna use
//   0------(40)------3------(2)------4
//   |                |               |  \
//   |                |               |   \
//  (10)             (10)            (3)  (8)
//   |                |               |     \
//   |                |               |      \  
//   1------(10)------2               5--(3)--6

public class main {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int s, int n, int w) {
            this.src = s;
            this.nbr = n;
            this.wt = w;
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int vces = 7;
        ArrayList<Edge>[] graph = new ArrayList[vces];
        for (int i = 0; i < vces; i++){
            graph[i] = new ArrayList<Edge>();
        }
    
// if u want to manually add the values int graph, this is how u do it;    
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 3, 40));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 2, 10));

        graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 3, 10));

        graph[3].add(new Edge(3, 0, 40));
        graph[3].add(new Edge(3, 2, 10));
        graph[3].add(new Edge(3,4,2));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 6, 8));

        graph[5].add(new Edge(5, 4, 3));
        graph[5].add(new Edge(5, 6, 3));

        graph[6].add(new Edge(6, 4, 8));
        graph[6].add(new Edge(6, 5, 3));

// if u want to take input for graph do it like this
        // int edges=Integer.parseInt(s.nextLine()); //this is to take the input of edges
        // for(int i=0;i<edges;i++){
        //     String[] parts=s.nextLine().split(" ");
        //     int v1=Integer.parseInt(parts[0]);
        //     int v2=Integer.parseInt(parts[1]);
        //     int wt=Integer.parseInt(parts[2]);
        //     graph[v1].add(new Edge(v1,v2,wt));
        //     graph[v2].add(new Edge(v2,v1,wt));
        // }
        int src=0;
        int des=2;
        boolean []visited=new boolean[vces];
        //System.out.println(hasPath(graph,src,des,visited));
        printAllPaths(graph,src,des,visited,"");
    }

// To check if there's a path present or not    
    public static boolean hasPath(ArrayList<Edge>[]graph,int src,int des,boolean[]visited){
        if(src==des) return true;
        visited[src]=true;
        for(Edge edge : graph[src]){
            if(!visited[edge.nbr]){
                boolean hasNbrPath=hasPath(graph,edge.nbr,des,visited);
                if(hasNbrPath) return true;
            }
        }
        return false;
    }
// To print all paths between src and des
    public static void printAllPaths(ArrayList<Edge>[]graph,int src,int des,boolean[] visited,String ans){
        if(src==des){
            System.out.println(ans);
            return;
        }
        visited[src]=true;
        for(Edge edge:graph[src]){
            if(!visited[edge.nbr]){
                printAllPaths(graph,edge.nbr,des,visited,ans+edge.src);
            }
        }
        visited[src]=false;
    }
}
