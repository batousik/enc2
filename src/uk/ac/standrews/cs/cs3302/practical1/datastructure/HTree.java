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
            this.addToTree(nytPointer, symbol);
            pointer = nytPointer;
        } else {
            if (!this.isMaxNumberInTheBlock(pointer)) {
                pointer = this.switchNodeWithHighest(pointer);
            }
        }
        pointer.incrementWeight();
        this.updateBlockMap(pointer);
        while (!pointer.isRoot()) {
            pointer = pointer.getParent();
            if (!this.isMaxNumberInTheBlock(pointer)) {
                pointer = this.switchNodeWithHighest(pointer);
            }
            pointer.incrementWeight();
            this.updateBlockMap(pointer);
        }
        return "";
    }

    /**
     * This procedure allows to add new, not yet transmitted symbol to the tree.
     *
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
        this.updateBlockMap(nytPointer.getRight());
    }

    /**
     * @param node
     */
    private void updateBlockMap(HNode node) {
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

    /**
     * @param nodes
     * @param node
     */
    private void removeNodeFromListInMap(SortedArrayList<HNode> nodes, HNode node) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getOrder() == node.getOrder()) {
                nodes.remove(i);
                break;
            }
        }
    }

    /**
     * Procedure simply checks if the order of the node is the highest order
     *
     * @param node node to evaluate
     * @return true if the node has the highest order in the block
     */
    private boolean isMaxNumberInTheBlock(HNode node) {
        int weight = node.getWeight();
        SortedArrayList<HNode> nodes = mapOfBlocks.get(weight);
        return node.getOrder() > nodes.get(nodes.size() - 1).getOrder();
    }

    /**
     * Procedure "swaps nodes" in the tree,
     * according to algorithm current node is swapped with
     * the highest order node in the same block
     * procedure only has to swap values of nodes,
     * actual nodes in the tree are kept.
     *
     * @param node node to be swapped with the highest node in the block
     * @return node with same value but highest order in the block
     */
    private HNode switchNodeWithHighest(HNode node) {
        int weight = node.getWeight();
        String value = node.getValue();
        SortedArrayList<HNode> nodes = mapOfBlocks.get(weight);
        HNode higherNode = nodes.get(nodes.size() - 1);
        node.setValue(higherNode.getValue());
        higherNode.setValue(value);
        return higherNode;
    }

    /**
     * @param symbol
     * @return
     */
    private HNode getPointer(String symbol) {
        return this.mapOfNodes.get(symbol);
    }
}
