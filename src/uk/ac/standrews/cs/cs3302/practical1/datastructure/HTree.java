package uk.ac.standrews.cs.cs3302.practical1.datastructure;

import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

import java.util.HashMap;

/**
 * Created by 130017964 on 10/15/15.
 */

public class HTree {
    private HNode huffmanTree;
    private HashMap<String, HNode> mapOfNodes;
    private HashMap<Integer, SortedArrayList<HNode>> mapOfBlocks;

    /**
     * @param alphabetSize
     * @throws TreeOverflowException
     */
    public HTree(int alphabetSize) throws TreeOverflowException {
        this.huffmanTree = new HNode(alphabetSize);
        this.mapOfNodes = new HashMap<>();
        this.mapOfNodes.put(HNode.NYT, this.huffmanTree);
        this.mapOfNodes.put(HNode.ROOT, this.huffmanTree);
        this.mapOfBlocks = new HashMap<>();
    }


    public String acceptSymbol(String symbol) throws TreeOverflowException {
        // check if the symbol already exists
        HNode pointer = this.getPointer(symbol);
        HNode nytPointer = this.getPointer(HNode.NYT);

        // if symbol is a new symbol
        if (pointer == null) {
            // add new node to the tree
            addToTree(nytPointer, symbol);
            pointer = nytPointer;
        }
        pointer.incrementWeight();

        updateTree(pointer);
        return "";
    }


    /**
     * This procedure allows to add new, not yet transmitted symbol to the tree.
     * @param nytPointer pointer to the NYT node
     * @param symbol     new, yet unseen symbol
     * @throws TreeOverflowException
     */
    private void addToTree(HNode nytPointer, String symbol) throws TreeOverflowException {
        // NYT gives birth to new NYT and new "External Node"
        nytPointer.setValue("");
        nytPointer.setLeft(new HNode(nytPointer));
        nytPointer.setRight(new HNode(nytPointer, symbol));
        // update node map
        this.mapOfNodes.put(HNode.NYT, nytPointer.getLeft());
        this.mapOfNodes.put(symbol, nytPointer.getRight());
        // increment weights of the new external node
        nytPointer.getRight().incrementWeight();
        // update block map
        this.addToBlockMap(nytPointer.getRight());
    }

    private void addToBlockMap(HNode node) {
        int weight = node.getWeight();
        SortedArrayList<HNode> nodes;
        // check if block with this weight exists in the tree
        if (this.mapOfBlocks.get(weight) == null) {
            nodes = new SortedArrayList<>();
            this.mapOfBlocks.put(weight, nodes);
        } else {
            nodes = this.mapOfBlocks.get(weight);
        }

        // add node to sorted list
        nodes.addSorted(node);

        if (weight != 1) {
            // weight is more than 1, not a new symbol
            // remove node from the old block
            nodes = this.mapOfBlocks.get(weight - 1);
            removeNodeFromListInMap(nodes, node);
        }
    }

    private void removeNodeFromListInMap(SortedArrayList<HNode> nodes, HNode node) {
    }


    /**
     * @param pointer
     * @return
     */
    private boolean isMaxNumberInTheBlock(HNode pointer) {
        //TODO: fix this
        return (pointer.getLeft().getWeight() + pointer.getRight().getWeight()) <= pointer.getWeight();
    }

    private void switchNodeWithHighest(HNode pointer) {

    }

    private void updateTree(HNode newNode) {

    }

    private HNode getPointer(String symbol) {
        return this.mapOfNodes.get(symbol);
    }

    private boolean isSiblingOfZERONode(HNode pointer) {
        return true;
        //return pointer.getParent().getLeft().getValue() == HNode.ZERO_NODE_VAL || pointer.getParent().getLeft().getValue() == HNode.ZERO_NODE_VAL;
    }

    private void slideAndIncrement(HNode pointer) {

    }

}
