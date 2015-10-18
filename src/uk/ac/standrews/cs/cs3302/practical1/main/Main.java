package uk.ac.standrews.cs.cs3302.practical1.main;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;
import uk.ac.standrews.cs.cs3302.practical1.encoder.HuffmanStringEncoder;
import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

/**
 * Created by 130017964 on 10/11/15.
 */
public class Main {
    public static void main(String[] args) {

        try {
            HuffmanStringEncoder he = new HuffmanStringEncoder("data_set_1.txt", 256);
            he.encode();
            System.out.println(he.getEncodedText());
            HTree huffmanTree = new HTree(257);
            String test = "abracadabra";
            String[] testArr = test.split("");
            for (int i = 1; i < testArr.length; i++) {
                huffmanTree.acceptSymbol(testArr[i]);
            }
//            huffmanTree.acceptSymbol("a");
//            huffmanTree.acceptSymbol("b");
//            huffmanTree.acceptSymbol("r");
//
////            huffmanTree.print();
////            System.out.println();
////            System.out.println();
//            huffmanTree.acceptSymbol("a");
//            huffmanTree.acceptSymbol("c");
//            huffmanTree.acceptSymbol("a");
//            huffmanTree.acceptSymbol("d");
//            huffmanTree.acceptSymbol("a");
//            huffmanTree.acceptSymbol("b");
//            huffmanTree.acceptSymbol("r");
//            huffmanTree.acceptSymbol("a");
//            huffmanTree.print();
//            System.out.println();

        } catch (TreeOverflowException e) {
            e.printStackTrace();
        }
//        //HTree treeManipulator = new HTree(huffmanTree);
//        // treeManipulator.acceptSymbol("a");
    }
}