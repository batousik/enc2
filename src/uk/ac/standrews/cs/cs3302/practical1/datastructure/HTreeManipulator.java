package uk.ac.standrews.cs.cs3302.practical1.datastructure;

import java.util.HashMap;

/**
 * Created by 130017964 on 10/15/15.
 */

public class HTreeManipulator {
    private HTree huffmanTree;
    private HashMap<String, HTree> mapOfNodes;

    public HTreeManipulator(HTree huffmanTree) {
        this.huffmanTree = huffmanTree;
        this.mapOfNodes = new HashMap<>();
        this.mapOfNodes.put(this.huffmanTree.getValue(), this.huffmanTree);
    }

    public void acceptSymbol(String symbol) {
        // check if the symbol already exists
        HTree pointer = this.getPointer(symbol);
        HTree zeroPointer = this.getPointer(HTree.ZERO_NODE_VAL);
        HTree pointerToIncrement = null;

        // if symbol is a new symbol
        if (pointer == null) {
            zeroPointer.setLeft(new HTree());
            zeroPointer.setRight(new HTree(symbol));
            pointer = zeroPointer;
            pointerToIncrement = zeroPointer.getRight();
        } else if (isSiblingOfZERONode(pointer)) {
            pointerToIncrement = pointer;
            pointer = pointer.getParent();
        }

        updateTree(pointer);

    }

    private void updateTree(HTree newNode) {

    }

    private HTree getPointer(String symbol) {
        return this.mapOfNodes.get(symbol);
    }

    private boolean isSiblingOfZERONode(HTree pointer) {
        return pointer.getParent().getLeft().getValue() == HTree.ZERO_NODE_VAL || pointer.getParent().getLeft().getValue() == HTree.ZERO_NODE_VAL;
    }

}
