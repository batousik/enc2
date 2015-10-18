package uk.ac.standrews.cs.cs3302.practical1.main;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;
import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

/**
 * Created by 130017964 on 10/11/15.
 */
public class Main {
    public static void main(String[] args) {
        try {
            HTree huffmanTree = new HTree(6);
            huffmanTree.acceptSymbol("a");
            huffmanTree.acceptSymbol("b");
            huffmanTree.acceptSymbol("r");

//            huffmanTree.print();
//            System.out.println();
//            System.out.println();
            huffmanTree.acceptSymbol("a");
            huffmanTree.acceptSymbol("c");
            huffmanTree.acceptSymbol("a");
            huffmanTree.acceptSymbol("d");
            huffmanTree.acceptSymbol("a");
            huffmanTree.acceptSymbol("b");
            huffmanTree.acceptSymbol("r");
            huffmanTree.acceptSymbol("a");
            huffmanTree.print();
            System.out.println();
            System.out.println();
            huffmanTree.print();


        } catch (TreeOverflowException e) {
            e.printStackTrace();
        }
        //HTree treeManipulator = new HTree(huffmanTree);
        // treeManipulator.acceptSymbol("a");

    }
}