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
        System.out.print(leftView(root));
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

    //
}
