package uk.ac.standrews.cs.cs3302.practical1.main;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;
import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTreeManipulator;

/**
 * Created by 130017964 on 10/11/15.
 */
public class Main {
    public static void main(String[] args) {
        HTree huffmanTree = new HTree();
        HTreeManipulator treeManipulator = new HTreeManipulator(huffmanTree);
        treeManipulator.acceptSymbol("a");

    }
}