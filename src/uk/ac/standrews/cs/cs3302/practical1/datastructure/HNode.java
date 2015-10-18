package uk.ac.standrews.cs.cs3302.practical1.datastructure;

import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 130017964 on 10/13/15.
 */
public class HNode implements Comparable {
    // value for the NYT node
    public static final String NYT = "NYT";
    // value for the ROOT node
    public static final String ROOT = "ROOT";
    // value for the inner node
    public static final String INNER = "INNER";

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
    private int level = 0;

    /**
     * Constructor for the first node, to initialise the tree
     *
     * @param alphabetSize the size of the alphabet to be transmitted. eg: english lcase letters = 26
     */
    public HNode(int alphabetSize) throws TreeOverflowException {
        initialiseOrderCount(alphabetSize);
        this.order = this.getNextOrder();
        this.value = HNode.ROOT;
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
        this.value = HNode.NYT;
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

    private static void breadthTraversal(HNode root) {

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

    public int getLevel() {
        if (isRoot())
            return this.level;
        return this.getParent().getLevel() + 1;
    }

    public void incrementWeight() {
        this.weight += 1;
    }

    public void print() {
        Queue<HNode> queue = new LinkedList<>();
        queue.add(this);
        int prevLevel = 0;
        while (!queue.isEmpty()) {
            HNode node = queue.remove();
            if (prevLevel < node.getLevel()) {
                System.out.println("");
                prevLevel = node.getLevel();
            }
            System.out.print(node + "    ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    @Override
    public int compareTo(Object o) {
        int extOrder = ((HNode) o).getOrder();
        int curOrder = getOrder();
        return (curOrder < extOrder) ? -1 : (curOrder > extOrder) ? 1 : 0;
    }

    @Override
    public String toString() {
        String more = "";
        if (this.getValue() != null) {
            if (!this.getValue().equals("")) {
                more = "\"" + this.getValue() + "\"";
            }
        }
        String before = "";
        if (!this.isRoot()) {
            before = this.getParent().getOrder() + "^";
        }
        if (this.getLeft() != null) {
            before += this.getLeft().getOrder() + "|";
        }
        if (this.getRight() != null) {
            before += this.getRight().getOrder() + "|";
        }
        return before + "(" + this.getOrder() + "-" + this.getWeight() + ")" + more;
    }
}
