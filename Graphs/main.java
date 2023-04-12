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

    public static void main(String[] args) {
        int vtces = 7;
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
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
        graph[3].add(new Edge(3, 4, 2));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 3));
        graph[4].add(new Edge(4, 6, 8));

        graph[5].add(new Edge(5, 4, 3));
        graph[5].add(new Edge(5, 6, 3));

        graph[6].add(new Edge(6, 4, 8));
        graph[6].add(new Edge(6, 5, 3));

        int src = 0;
        int des = 6;
        boolean[] visited = new boolean[vtces];
        int k = 3;
        int criteria = 40; // for ceil and floor

        // System.out.println(hasPath(graph,src,des,visited));

        // printAllPaths(graph,src,des,visited,"");

        //multisolver(graph, src, des, visited, criteria, k, "" + src, 0);
        //printVal();

        //to get connected elements
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int i = 0; i < vtces; i++) {
            if (!visited[i]) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                drawTree(graph, i, visited, list);
                comps.add(list);
            }
        }
        System.out.println(comps);
        // to check if the graph is connected, check the size of comps, if size is one that means graph is connected

    }

    // To check if there's a path present or not
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int des, boolean[] visited) {
        if (src == des)
            return true;
        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (!visited[edge.nbr]) {
                boolean hasNbrPath = hasPath(graph, edge.nbr, des, visited);
                if (hasNbrPath)
                    return true;
            }
        }
        return false;
    }

    // To print all paths between src and des
    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int des, boolean[] visited, String ans) {
        if (src == des) {
            System.out.println(ans);
            return;
        }
        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (!visited[edge.nbr]) {
                printAllPaths(graph, edge.nbr, des, visited, ans + edge.src);
            }
        }
        visited[src] = false;
    }

    // Smallest, longest, ceil, floor, kth largest path (Breadth First Search)
    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void printVal() {
        System.out.println("Smallest path " + spath + "@" + spathwt);
        System.out.println("Longest path " + lpath + "@" + lpathwt);
        System.out.println("Ceil path " + cpath + "@" + cpathwt);
        System.out.println("Floor path " + fpath + "@" + fpathwt);
        System.out.println("KthLargest path " + pq.peek().psf + "@" + pq.peek().wsf);
    }

    static class Pair implements Comparable<Pair> {
        String psf;
        int wsf;

        Pair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void multisolver(ArrayList<Edge>[] graph, int src, int des, boolean[] visited, int criteria, int k,
            String psf, int wsf) {
        if (src == des) {
            if (wsf < spathwt) {
                spathwt = wsf;
                spath = psf;
            }
            if (wsf > lpathwt) {
                lpathwt = wsf;
                lpath = psf;
            }
            if (wsf > criteria && wsf < cpathwt) {
                cpathwt = wsf;
                cpath = psf;
            }
            if (wsf < criteria && wsf > fpathwt) {
                fpathwt = wsf;
                fpath = psf;
            }
            if (pq.size() < k) {
                pq.add(new Pair(wsf, psf));
            } else if (wsf > pq.peek().wsf) {
                pq.remove();
                pq.add(new Pair(wsf, psf));
            }
        }

        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                multisolver(graph, e.nbr, des, visited, criteria, k, psf + e.nbr, wsf + e.wt);
            }
        }
        visited[src] = false;
    }

    // To get connected components
    public static void drawTree(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> list) {
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                list.add(e.nbr);
                drawTree(graph, e.nbr, visited, list);
            }
        }
    }

    //   To get connected components 
}
