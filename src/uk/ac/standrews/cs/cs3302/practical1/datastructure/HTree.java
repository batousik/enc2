package uk.ac.standrews.cs.cs3302.practical1.datastructure;

/**
 * Created by 130017964 on 10/13/15.
 */
public class HTree {
    // constant val for the ZERO node
    public static final String ZERO_NODE_VAL = "NYT";
    // actual data piece
    private String value;
    // how many times value occured so far
    private int weight;
    // helps track weights
    private int order;

    // tree structure implementation
    private HTree left;
    private HTree right;
    private HTree parent;

    // constructor for root, ZERO node
    public HTree() {
        // ZERO node has zero weight
        this.weight = 0;
        this.value = HTree.ZERO_NODE_VAL;
    }

    // constructor for leaf node
    public HTree(String value) {
        this.value = value;
    }

    // default constructor
    public HTree(String value, int weight, int order, HTree left, HTree right, HTree parent) {
        this.value = value;
        this.weight = weight;
        this.order = order;
        this.left = left;
        this.right = right;
        this.parent = parent;
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

    public HTree getLeft() {
        return left;
    }

    public void setLeft(HTree left) {
        this.left = left;
    }

    public HTree getRight() {
        return right;
    }

    public void setRight(HTree right) {
        this.right = right;
    }

    public HTree getParent() {
        return parent;
    }

    public void setParent(HTree parent) {
        this.parent = parent;
    }
}
