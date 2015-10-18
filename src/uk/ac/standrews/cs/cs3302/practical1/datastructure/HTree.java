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
            HNode higherNode = this.isNotMaxNumberInTheBlockAndIsMaxNotParent(pointer);
            if (higherNode != null)
                pointer = this.switchNodeWithHighest(pointer, higherNode);
        }
        pointer.incrementWeight();
        this.updateBlockMap(pointer);
        while (!pointer.isRoot()) {
            pointer = pointer.getParent();
            HNode higherNode = this.isNotMaxNumberInTheBlockAndIsMaxNotParent(pointer);
            if (higherNode != null)
                pointer = this.switchNodeWithHighest(pointer, higherNode);
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
        if (nytPointer.isRoot()) {
            nytPointer.setValue(HNode.ROOT);
        } else {
            nytPointer.setValue(HNode.INNER);
        }
        nytPointer.setRight(new HNode(nytPointer, symbol));
        nytPointer.setLeft(new HNode(nytPointer));
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
     * Procedure checks if the order of the node is the highest order
     * and if not highest also checks if the highest order is not parent of the node
     *
     * @param node node to evaluate
     * @return higher node if the node doesn't have the highest order in the block and highest order is not parent
     * null otherwise
     */
    private HNode isNotMaxNumberInTheBlockAndIsMaxNotParent(HNode node) {
        int weight = node.getWeight();
        SortedArrayList<HNode> nodes = mapOfBlocks.get(weight);
        for (int i = nodes.size() - 1; i >= 0; i--) {
            HNode n = nodes.get(i);
            if (n.getOrder() <= node.getOrder())
                return null;
            if (n.getOrder() != node.getParent().getOrder())
                return n;
        }
        return null;
    }


    /**
     * Procedure "swaps nodes" in the tree,
     * according to algorithm current node is swapped with
     * the highest order node in the same block
     * procedure only has to swap values of nodes,
     * actual nodes in the tree are kept, to keep orders right
     *
     * @param node       node to be swapped with the highest node in the block
     * @param higherNode highestNode with which we swap
     * @return node with same value but highest order in the block
     */
    private HNode switchNodeWithHighest(HNode node, HNode higherNode) {
        System.out.println(node);
        System.out.println(higherNode);
        String value = node.getValue();
        //int tempWeight = node.getWeight();
        HNode tempLeft = node.getLeft();
        HNode tempRight = node.getRight();
        node.setValue(higherNode.getValue());
        node.setLeft(higherNode.getLeft());
        node.setRight(higherNode.getRight());
        node.setWeight(higherNode.getWeight());
        higherNode.setValue(value);
        higherNode.setRight(tempRight);
        higherNode.setLeft(tempLeft);
        //higherNode.setWeight(tempWeight);
        // for each child of swapped nodes if they exist, update their parent
        if (higherNode.getLeft() != null)
            higherNode.getLeft().setParent(node);
        if (higherNode.getRight() != null)
            higherNode.getRight().setParent(node);
        if (tempRight != null)
            tempRight.setParent(higherNode);
        if (tempLeft != null)
            tempLeft.setParent(higherNode);
        /* also update map of nodes
        * map of blocks doesn't need to be updated since
        * we are swapping nodes in the same block
        * and don't change orders
        */
        System.out.println(mapOfBlocks.get(node.getWeight()));
        System.out.println(node);
        System.out.println(higherNode);
        System.out.println();
        this.mapOfNodes.put(node.getValue(), node);
        this.mapOfNodes.put(higherNode.getValue(), higherNode);
        return higherNode;
    }

    /**
     * @param symbol
     * @return
     */
    private HNode getPointer(String symbol) {
        return this.mapOfNodes.get(symbol);
    }

    public void print() {
        this.huffmanTree.print();
    }

    @Override
    public String toString() {
        return "";
    }
}
