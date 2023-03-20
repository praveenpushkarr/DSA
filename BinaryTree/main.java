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
        preorderTraversal(root);
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
        if(root==null) return;
        System.out.print(root.data+" ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return;
    }
    // For Posteorder traversal (left subtree -> right subtree -> root)
    public static void postorderTraversal(Node root) {
        if(root==null) return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.data+" ");
        return;
    }
}
