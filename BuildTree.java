package src;

import java.util.*;

public class BuildTree {
    static class Node {
            int value;
            Node left;
            Node right;

            Node(int value) {
                this.value = value;
                this.left = null;
                this.right = null;
            }
    }

    public static Node buildTree(int[] I, int[] P) {

        //base case(s)
        if (I.length == 0) {
            return null;
        }
        if (I.length != P.length) {
            return null;
        }

        int n = I.length;
        int rootIndex = -1;

        //find root of binary tree
        int root = P[P.length - 1]; //root of tree taken from last element in postorder list
        for (int i = 0; i < n; i++) {
            if (I[i] == root) {
                rootIndex = i;
                break;
            }

            if (rootIndex == -1) {
                return null;
            }
        }

        //split inorder list from root
        int[] leftInOrder = new int[rootIndex];
        int[] rightInOrder = new int[n - rootIndex - 1];
        
        for (int j = 0; j < rootIndex; j++) {
            leftInOrder[j] = I[j];
        }
        for (int j = rootIndex + 1; j < n; j++) {
            rightInOrder[j - rootIndex - 1] = I[j];
        }

        //create postorder left and right subtree lists and compare to recently made lists of inorder
        int leftSize = leftInOrder.length;

        int[] leftPostOrder = new int[leftSize];
        int[] rightPostOrder = new int[rightInOrder.length];

        for (int i = 0; i < leftSize - 1; i++) {
            leftPostOrder[i] = P[i]; 
        }
        for (int i = leftSize; i < n - 2; i++) {
            rightPostOrder[i - leftSize] = P[i]; 
        }

        //compare inorder and postorder lists
        if (!toSet(leftInOrder).equals(toSet(leftPostOrder))) {
            return null;
        }

        if (!toSet(rightInOrder).equals(toSet(rightPostOrder))) {
            return null;
        }

        //recursively repeat above steps for left subtree
        Node leftChild = buildTree(leftInOrder, leftPostOrder);

        //recursrively repeat above steps for right subtree
        Node rightChild = buildTree(rightInOrder, rightPostOrder);

        //attach two resulting subtrees to the root
        Node T = new Node(root);

        T.left = leftChild;
        T.right = rightChild;

        return T;

    }

    //helper method
    public static Set<Integer> toSet(int[] array) {
        Set<Integer> set = new HashSet<>();

        for (int value : array) {
            set.add(value);
        }

        return set;
    }

    public static void runTest(int[] inorder, int[] postorder, String testName) {
        System.out.println("Test: " + testName);

        Node root = buildTree(inorder, postorder);

        if (root == null) {
            System.out.println("Result: No tree / no soution");
        } else {
            System.out.println("Result: Tree constructed successfully");
        }

        System.out.println();
    }

    public static int[] generateInorder(int n) {
        int[] inorder = new int[n];

        for (int i = 0; i < n; i++) {
            inorder[i] = i + 1;
        }

        return inorder;
    }

    public static int[] generatePostorder(int n) {
        int[] postorder = new int[n];

        for (int i = 0; i < n; i++) {
            postorder[i] = n - i;
        }

        return postorder;
    }

    public static void main(String[] args) {

        //empty tree
        runTest(new int[] {}, new int[] {}, "Empty Tree");

        //no solution
        runTest(new int[] {1, 2, 3}, new int[] {4, 5, 6}, "No solution");

        //n = 20
        runTest(generatePostorder(20), generateInorder(20), "n = 20");

        //n = 50
        runTest(generatePostorder(50), generateInorder(50), "n = 50");

        //n = 100
        runTest(generatePostorder(100), generateInorder(100), "n = 100");

        //n = 200
        runTest(generatePostorder(200), generateInorder(200), "n = 200");
    }
}