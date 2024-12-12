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
        return search(root, val);
    }
    public boolean search(BSTNode root, int val) {
        if (root == null) {
            return false;
        }
        if(root.getVal() == val) {
            return true;
        }
        if(root.getVal() > val){
          return search(root.getRight(), val);
        }
       if(root.getVal() < val){
          return search(root.getLeft(), val);
        }
       return false;
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    public void inorderHelper(BSTNode root, ArrayList<BSTNode> result) {
        if (root != null) {
            inorderHelper(root.getLeft(), result);
            result.add(root);
            inorderHelper(root.getRight(), result);
        }
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    public void preorderHelper(BSTNode root, ArrayList<BSTNode> result) {
        if (root != null) {
            result.add(root);
            preorderHelper(root.getLeft(), result);
            preorderHelper(root.getRight(), result);
        }
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    public void postorderHelper(BSTNode root, ArrayList<BSTNode> result) {
        if (root != null) {
            postorderHelper(root.getLeft(), result);
            postorderHelper(root.getRight(), result);
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
        root = insertHelper(root, val);
    }
    public BSTNode insertHelper(BSTNode root, int val) {
        if (root == null) {
            return new BSTNode(val);
        }
        if (val < root.getVal()) {
            root.setLeft(insertHelper(root.getLeft(), val));
        } else if (val > root.getVal()) {
            root.setRight(insertHelper(root.getRight(), val));
        }
        return root;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean isValidBSTHelper(BSTNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.getVal() <= min || node.getVal() >= max) {
            return false;
        }
        return isValidBSTHelper(node.getLeft(), min, node.getVal()) && isValidBSTHelper(node.getRight(), node.getVal(), max);
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
