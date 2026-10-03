package src;
import java.util.*;

public class BuildTree {
    public static Node buildTree(int[] I, int[] P) {

        //base case(s)
        if (I.length == 0) {
            return null;
        }
        if (I.length != P.length) {
            return null;
        }

        int n = I.length;
        int rootIndex = 0;

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
        for (int i = 0; i < I.; i++){
            int[] leftInOrder = new int[i];
        }
        int[] rightInOrder;
        for (int j = 0; j < rootIndex - 1; j++) {
            leftInOrder[j] = I[j];
        }
        for (int j = 0; j < rootIndex + 1; j++) {
            rightInOrder[j - rootIndex - 1] = I[j];
        }

        //create postorder left and right subtree lists and compare to recently made lists of inorder
        int leftSize = leftInOrder.length; // ?

        int[] leftPostOrder;
        int[] rightPostOrder;
        for (int i = 0; i < leftSize - 1; i++) {
            leftPostOrder[i] = P[i]; 
        }
        for (int i = leftSize; i < n - 2; i++) {
            rightPostOrder[i - leftSize] = P[i]; 
        }

        //compare inorder and postorder lists
        if (set(leftInOrder) != set(leftPostOrder)) {
            return null;
        }
        if (set(rightInOrder) != set(rightPostOrder)) {
            return null;
        } 

        //recursively repeat above steps for left subtree
        leftChild = buildTree(leftInOrder, leftPostOrder);

        //recursrively repeat above steps for right subtree
        rightChild = buildTree(rightInOrder, rightPostOrder);

        //attach two resulting subtrees to the root
        

    }

    public static void main(String[] args) {

    }
}