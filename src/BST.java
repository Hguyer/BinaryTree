import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * Hunter Guyer
 * 12/12/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        //use the helper function
        return searchHelper(root, val);
    }
    public boolean searchHelper(BSTNode root, int val) {
        // base cases of if the root is equal to the value or if it is null
        if (root == null) {
            return false;
        }
        if(root.getVal() == val) {
            return true;
        }
        // if it isn't either then check if it's greather than val in which case search to the left
        if(val < root.getRight()){
          return searchHelper(root.getLeft(), val);
        }
        // if its val is greater search to the right
        return searchHelper(root.getRight(), val);
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }
    public void inorder(BSTNode root, ArrayList<BSTNode> result) {
        if (root != null) {
            // print the result in the middle
            inorder(root.getLeft(), result);
            result.add(root);
            inorder(root.getRight(), result);
        }
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }
    public void preorder(BSTNode root, ArrayList<BSTNode> result) {
        if (root != null) {
            // print the result before
            result.add(root);
            preorder(root.getLeft(), result);
            preorder(root.getRight(), result);
        }
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    public void postorder(BSTNode root, ArrayList<BSTNode> result) {
        if (root != null) {
            // print the result after
            postorder(root.getLeft(), result);
            postorder(root.getRight(), result);
            result.add(root);
        }
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        root = insert(root, val);
    }
    public BSTNode insert(BSTNode root, int val) {
        // base case if the root is null add your new root with a value of val there
        if (root == null) {
            return new BSTNode(val);
        }
        // if the val is less then the root recurse left :)
        if (val < root.getVal()) {
            root.setLeft(insert(root.getLeft(), val));
        }
        //if the val is > the root val recurse right
        else if (val > root.getVal()) {
            root.setRight(insert(root.getRight(), val));
        }
        return root;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean isValidBST(BSTNode root, int min, int max) {
        // if the node is null then return true
        if (root == null) {
            return true;
        }
        // of the node is outside of the max or min values an int can hold return false
        if (root.getVal() <= min || root.getVal() >= max) {
            return false;
        }
        // return true or false based off the info that recursivly checks down the array if all the values are the bounds
        // an int can hold
        return isValidBST(root.getLeft(), min, root.getVal()) && isValidBST(root.getRight(), root.getVal(), max);
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
