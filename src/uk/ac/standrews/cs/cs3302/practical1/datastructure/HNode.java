package uk.ac.standrews.cs.cs3302.practical1.datastructure;

import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

/**
 * Created by 130017964 on 10/13/15.
 */
public class HNode {
    // value for the NYT node
    public static final String NYT = "NYT";
    // value for the ROOT node
    public static final String ROOT = "ROOT";

    private static int currOrder;
    // actual data piece
    private String value;
    // how many times value occured so far
    private int weight;
    // helps track weights
    private int order;

    // tree structure implementation
    private HNode left;
    private HNode right;
    private HNode parent;
    private boolean isRoot = false;
    private boolean isNYT = false;

    /**
     * Constructor for the first node, to initialise the tree
     *
     * @param alphabetSize the size of the alphabet to be transmitted. eg: english lcase letters = 26
     */
    public HNode(int alphabetSize) throws TreeOverflowException {
        initialiseOrderCount(alphabetSize);
        this.order = this.getNextOrder();
        this.value = null;
        this.weight = 0;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isRoot = true;
        this.isNYT = true;
    }

    /**
     * Constructor for NYT node, not yet transmitted
     *
     * @param parent a parent of this new node
     */
    public HNode(HNode parent) throws TreeOverflowException {
        // NYT node has zero weight
        this.weight = 0;
        this.value = null;
        this.order = this.getNextOrder();
        this.left = null;
        this.right = null;
        this.parent = parent;
        this.isNYT = true;
    }

    /**
     * Constructor for the leaf node, that carries
     * one of the values from the given alphabet to encode
     *
     * @param value  value of the symbol from the alphabet
     * @param parent parent node of this node
     */
    public HNode(HNode parent, String value) throws TreeOverflowException {
        this.weight = 0;
        this.value = value;
        this.order = this.getNextOrder();
        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    /**
     * Default constructor
     *
     * @param value  value of the symbol from the alphabet
     * @param weight number of occurrences this value was seen
     * @param order  special order value
     * @param left   left child
     * @param right  right child
     * @param parent parent node of this node
     */
    public HNode(String value, int weight, int order, HNode left, HNode right, HNode parent) {
        this.value = value;
        this.weight = weight;
        this.order = order;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * Initialises the max number of nodes that could exist in this tree
     *
     * @param alphabetSize the size of the alphabet to be transmitted. eg: english lcase letters = 26
     */
    private void initialiseOrderCount(int alphabetSize) {
        currOrder = alphabetSize * 2 - 1;
    }

    private int getNextOrder() throws TreeOverflowException {
        int toReturn = currOrder;
        if (toReturn == 0) {
            throw new TreeOverflowException();
        }
        currOrder -= 1;
        return toReturn;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public HNode getLeft() {
        return left;
    }

    public void setLeft(HNode left) {
        this.left = left;
    }

    public HNode getRight() {
        return right;
    }

    public void setRight(HNode right) {
        this.right = right;
    }

    public HNode getParent() {
        return parent;
    }

    public void setParent(HNode parent) {
        this.parent = parent;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public boolean isNYT() {
        return isNYT;
    }

    public void incrementWeight() {
        this.weight += 1;
    }
}
