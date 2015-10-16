package uk.ac.standrews.cs.cs3302.practical1.main;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HNode;
import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;

/**
 * Created by 130017964 on 10/11/15.
 */
public class Main {
    public static void main(String[] args) {
        HNode huffmanTree = new HNode();
        HTree treeManipulator = new HTree(huffmanTree);
        treeManipulator.acceptSymbol("a");

    }
}