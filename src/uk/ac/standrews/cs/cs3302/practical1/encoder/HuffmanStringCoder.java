package uk.ac.standrews.cs.cs3302.practical1.encoder;

import uk.ac.standrews.cs.cs3302.practical1.datastructure.HTree;
import uk.ac.standrews.cs.cs3302.practical1.exceptions.TreeOverflowException;

import java.util.HashMap;

/**
 * Created by 130017964 on 10/13/15.
 * Super class fro huffman Decoder and Encoder
 */
public class HuffmanStringCoder {
    public static final int ALPHABET_SIZE = 96;
    public static final int ALPHABET_ASCII_START_CODE = 32;
    public static final int ALPHABET_ASCII_END_CODE = 126;

    protected HTree huffmanTree;
    protected int e;
    protected int r;
    protected String encodedData;
    protected HashMap<String, Integer> mapOfCharsToPos;
    protected HashMap<Integer, String> mapOfPosToChars;

    public HuffmanStringCoder(String encodedData) {
        try {
            this.encodedData = encodedData;
            this.mapOfCharsToPos = new HashMap<>();
            this.mapOfPosToChars = new HashMap<>();
            this.fillInCharsAndPosMap();

            this.huffmanTree = new HTree(HuffmanStringCoder.ALPHABET_SIZE);
            this.solveEncEquation();
        } catch (TreeOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates map to get chars position (k) from map of chars
     * Symbol S(k) is encoded as follows:
     * 1<=k<=2r as (e+1) bit binary representation
     * k>2r as e bit binary representation of k-r-1
     */
    protected void fillInCharsAndPosMap() {
        for (int i = HuffmanStringCoder.ALPHABET_ASCII_START_CODE; i <= HuffmanStringCoder.ALPHABET_ASCII_END_CODE; i++) {
            mapOfCharsToPos.put(String.valueOf(Character.toChars(i)), i - 31);
            mapOfPosToChars.put(i - 31, String.valueOf(Character.toChars(i)));
        }
    }

    /**
     * solves for e and r such that
     * 2^e + r = ALPHABET_SIZE and 0 <= r <= 2^e
     */
    protected boolean solveEncEquation() {
        for (int i = 0; i < HuffmanStringCoder.ALPHABET_SIZE; i++) {
            r = i;
            e = (int) (Math.log(HuffmanStringCoder.ALPHABET_SIZE - i) / Math.log(2));
            if (Math.pow(2, e) + r == HuffmanStringCoder.ALPHABET_SIZE)
                return true;
        }
        return false;
    }

    /**
     * Symbol S(k) is encoded as follows:
     * 1<=k<=2r as (e+1) bit binary representation of k-1
     * k>2r as e bit binary representation of k-r-1
     *
     * @return code representation of a char
     */

    public String getCodeForNewChar(String s) throws Exception {
        String codeForNewChar = null;
        try {
            int k = this.mapOfCharsToPos.get(s);
            int numOfBits;

            if (1 <= k && k <= 2 * r) {
                numOfBits = e + 1;
                codeForNewChar = Integer.toBinaryString(k - 1);
                while (codeForNewChar.length() < numOfBits)
                    codeForNewChar = 0 + codeForNewChar;
            } else {
                numOfBits = e;
                codeForNewChar = Integer.toBinaryString(k - r - 1);
                while (codeForNewChar.length() < numOfBits)
                    codeForNewChar = 0 + codeForNewChar;
            }
        } catch (NullPointerException npe) {
            System.err.println("ERROR FOR CHARACTER: [" + s + "] " + s.getBytes());
            throw new Exception(npe);
        }
        return codeForNewChar;
    }
}
