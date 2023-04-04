package BinaryTree;

import java.util.*;
// Binary Tree: A tree with nodes having either zero, one or two child nodes only.
// ex-                              1
//                  2                               3
//          4               5               6               7 

//creating binary tree recurssively
public class main {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int d) {
            this.data = d;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int i = -1;

        public static Node buildTree(Integer[] nodes) {
            i++;
            if (nodes[i] == null)
                return null;

            Node newNode = new Node(nodes[i]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    public static void main(String[] args) {
        Integer[] nodes = { 1, 2, 4, null, null, 5, null, null, 3, 6, null, null, 7, null, null };
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        Diameter(root);
    }

    // Height of binary tree
    public static int Height(Node root){
        if(root==null) return 0;
        
        int lh=Height(root.left);
        int rh=Height(root.right);
        return 1+Math.max(lh,rh);
    }

    // for Diameter, left height of subtree+right height of subtree;
    public static void Diameter(Node root){
        int []d=new int[1];
        height(root,d);
        System.out.print(d[0]);
    }
    public static int height(Node root,int []d){
        if(root==null) return 0;
        
        int lh=height(root.left,d);
        int rh=height(root.right,d);
        d[0]=Math.max(d[0],lh+rh);
        return 1+Math.max(lh,rh);
    }

    // For Inorder traversal (left subtree -> root -> right subtree)
    public static void inorderTraversal(Node root) {
        if (root == null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
        return;
    }

    // For Preorder traversal (root -> left subtree -> right subtree)
    public static void preorderTraversal(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return;
    }

    // For Postorder traversal (left subtree -> right subtree -> root)
    public static void postorderTraversal(Node root) {
        if (root == null)
            return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.data + " ");
        return;
    }

    // For level order traversal
    // i.e 1
    // 2 3
    // 4 5 6 7
    public static void levelorderTraversal(Node root) {
        Queue<Node> q = new LinkedList<>();
        if (root == null)
            return;

        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                if (q.peek().left != null) {
                    q.add(q.peek().left);
                }
                if (q.peek().right != null) {
                    q.add(q.peek().right);
                }
                System.out.print(q.remove().data + " ");
            }
            System.out.println();
        }
    }

    // Node to Root path
    static ArrayList<Integer> ans;
    public static boolean find(Node node, int data) {
        if (node == null) {
            return false;
        }
        if (node.data == data) {
            ans.add(node.data);
            return true;
        }
        boolean filc = find(node.left, data);
        if (filc) {
            ans.add(node.data);
            return true;
        }
        boolean firc = find(node.right, data);
        if (firc) {
            ans.add(node.data);
            return true;
        }
        return false;
    }

    // For left view of tree
    public static ArrayList<Integer> leftView(Node root){
      ArrayList<Integer> list=new ArrayList<>();
      if(root==null) return list;
      Queue<Node> q=new LinkedList<>();
      q.add(root);
      while(!q.isEmpty()){
          int size=q.size();
          list.add(q.peek().data);
          for(int i=0;i<size;i++){
              if(q.peek().left!=null) q.add(q.peek().left);
              if(q.peek().right!=null) q.add(q.peek().right);
              q.remove();
          }
      }
      return list;
    }
    // For right view of tree, just add right node first in queue

    // For Bottom view 
    class Pair{
        int hd;
        Node root;
        Pair(int hd,Node root){
            this.hd=hd;
            this.root=root;
        }
    }
    public ArrayList <Integer> bottomView(Node root)
    {
        ArrayList<Integer> list=new ArrayList<>();
        if(root==null) return list;
        Map<Integer,Integer> tm=new TreeMap<>();
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(0,root));
        while(!q.isEmpty()){
            Pair curr=q.remove();
            // if(!tm.containsKey(curr.hd)){  //this will give the top view
                tm.put(curr.hd,curr.root.data);
            // }
            if(curr.root.left!=null) q.add(new Pair(curr.hd-1,curr.root.left));
            if(curr.root.right!=null) q.add(new Pair(curr.hd+1,curr.root.right));
        }
        for(int key:tm.keySet()){
            list.add(tm.get(key));
        }
        return list;
    }

    //For vertical-order traversal,There may be multiple nodes in the same row and column.In such case, sort these nodes by their values.
    class Tuple{
        Node root;
        int x;
        int y;
        Tuple(Node root,int x,int y){
            this.root=root;
            this.x=x;
            this.y=y;
        }
    }
    public List<List<Integer>> verticalTraversal(Node root) {
        List<List<Integer>> list=new ArrayList<>();
        Map<Integer,Map<Integer,PriorityQueue<Integer>>> map=new TreeMap<>();
        Queue<Tuple> q=new LinkedList<>();
        Tuple rt=new Tuple(root,0,0);
        q.add(rt);
        while(!q.isEmpty()){
            Tuple curr=q.remove();
            if(!map.containsKey(curr.x)){
                map.put(curr.x,new TreeMap<Integer,PriorityQueue<Integer>>());
            }
            if(!map.get(curr.x).containsKey(curr.y)){
                map.get(curr.x).put(curr.y,new PriorityQueue<Integer>());
            }
            map.get(curr.x).get(curr.y).add(curr.root.data);
            if(curr.root.left!=null){
                q.add(new Tuple(curr.root.left,curr.x-1,curr.y+1));
            }
            if(curr.root.right!=null){
                q.add(new Tuple(curr.root.right,curr.x+1,curr.y+1));
            }
        }
        for(int keyx:map.keySet()){
            List<Integer>subList=new ArrayList<>();
            for(int keyy:map.get(keyx).keySet()){
                while(!map.get(keyx).get(keyy).isEmpty()){
                    subList.add(map.get(keyx).get(keyy).remove());
                }
            }
            list.add(subList);
        }
        return list;
    }

    // 
    }
}
