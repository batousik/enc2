package test.java;

import org.junit.Test;
import uk.ac.standrews.cs.cs3302.practical1.datastructure.HNode;
import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;
import uk.ac.standrews.cs.cs3302.practical1.datastructure.SortedArrayList;
import uk.ac.standrews.cs.cs3302.practical1.encoder.HuffmanStringCoder;
import uk.ac.standrews.cs.cs3302.practical1.encoder.HuffmanStringDecoder;
import uk.ac.standrews.cs.cs3302.practical1.encoder.HuffmanStringEncoder;
import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by 130017964 on 10/13/15.
 * Main Test suit for the practical
 */
public class TestSuite {

    @Test
    public void testSortedArrLst() {
        SortedArrayList<Integer> list = new SortedArrayList<>();
        list.addSorted(5);
        list.addSorted(4);
        list.addSorted(3);
        list.addSorted(2);
        list.addSorted(54);
        list.addSorted(22);
        list.addSorted(1);
        list.addSorted(67);
        list.addSorted(66);
        list.addSorted(44);
        for (int i = 0; i < list.size() - 1; i++) {
            int x = list.get(i);
            int y = list.get(i + 1);
            assertTrue(x < y);
        }
    }

    @Test
    public void testCompareHNode() {
        HNode hNode1 = new HNode("a", 1, 233, null, null, null);
        HNode hNode2 = new HNode("a", 1, 235, null, null, null);
        HNode hNode3 = new HNode("a", 1, 235, null, null, null);
        HNode hNode4 = new HNode("a", 1, 1, null, null, null);
        assertTrue(hNode1.compareTo(hNode1) == 0);
        assertTrue(hNode1.compareTo(hNode2) == -1);
        assertTrue(hNode2.compareTo(hNode3) == 0);
        assertTrue(hNode3.compareTo(hNode4) == 1);
    }

    @Test
    public void testEncoderPattern() throws TreeOverflowException {
        HTree huffmanTree = new HTree(257);
        String test = "abracadabra";
        String[] testArr = test.split("");
        for (int i = 1; i < testArr.length; i++) {
            huffmanTree.acceptSymbol(testArr[i]);
        }
        assertEquals("1001", huffmanTree.getCodeRepr("d"));
    }

    @Test
    public void testHuffmanCoder() throws TreeOverflowException, IOException {
        final String data = "A !\"#$%&'()*+,-./0123456789:;<=>?@ABCD" +
                "EFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|" +
                "}~139r87yAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        HuffmanStringEncoder he = new HuffmanStringEncoder(data);
        he.encode();
        HuffmanStringDecoder hd = new HuffmanStringDecoder(he.getEncodedText(), "out.txt");
        hd.decode();
        assertEquals(data, hd.getDecodedData());
    }

    @Test
    public void testAllowedAlphabet() {
        final String symbols = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String toCollect = "";
        int counter = 0;
        for (int i = HuffmanStringCoder.ALPHABET_ASCII_START_CODE; i <= HuffmanStringCoder.ALPHABET_ASCII_END_CODE; i++) {
            toCollect += String.valueOf(Character.toChars(i));
            counter++;
        }
        System.out.println("SYMBOLS: " + counter);
        assertEquals(toCollect, symbols);

    }
}
